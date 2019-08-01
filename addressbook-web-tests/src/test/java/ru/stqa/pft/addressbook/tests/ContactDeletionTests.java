package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
/*            app.contact().create(new ContactData("Ivanovich", "Ivanov", "AgentSmith",
                    "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07",
                    "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com",
                    "test2@mail.com", "test3@mail.com", "localhost", "5", "March",
                    "2047", "Ivan", "6", "May", "2017", "test1",
                    "Lenina street, 35", "Some notes", "35-18-29"), true);*/

            app.contact().create(new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith").withTitle("Some Title").withCompany("The Matrix")
                    .withAddress("K. Marksa street, 18").withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
                    .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
                    .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
                    .withAmonth("May").withAyear("2017").withGroup("test1").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29"), true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
