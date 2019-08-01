package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith").withTitle("Some Title").withCompany("The Matrix")
                    .withAddress("K. Marksa street, 18").withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
                    .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
                    .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
                    .withAmonth("May").withAyear("2017").withGroup("test1").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29"), true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
