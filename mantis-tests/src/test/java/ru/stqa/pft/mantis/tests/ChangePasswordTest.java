package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String loginAdmin = "administrator";
        String passwordAdmin = "root";
        String passwordUsrOld = "password";
        String passwordUsrNew = "password123";

        Users users = app.db().users();
        UserData user = users.iterator().next();
        System.out.println(user.getUsername());
        if (app.james().doesUserExist(user.getUsername())){
            app.james().deleteUser(user.getUsername());
        }
        app.james().createUser(user.getUsername(), passwordUsrOld);
        app.registration().login(loginAdmin, passwordAdmin);
        app.registration().selectUser(user.getUsername(), passwordAdmin);
        app.registration().resetPassword();
        app.james().drainEmail(user.getUsername(), passwordUsrOld);

        List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), passwordUsrOld, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, passwordUsrNew);
        HttpSession session = app.newSession();
        assertTrue( session.login(user.getUsername(), passwordUsrNew));
        assertTrue(session.isLoggedInAs(user.getUsername()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).collect(Collectors.toList()).get(mailMessages.size()-1);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
