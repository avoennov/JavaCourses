package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith")
                    .withPhoto(new File("src/test/resources/kitten-pizza.jpg")).withTitle("Some Title").withCompany("The Matrix")
                    .withAddress("K. Marksa street, 18").withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
                    .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
                    .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
                    .withAmonth("May").withAyear("2017").withGroup("test 1").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29"), true);
        }
    }

    @Test(enabled = true)
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withMiddlename("Petrovich").withLastname("Petrov")
                .withNickname("Neo").withPhoto(new File("src/test/resources/kitten-pizza.jpg")).withTitle("Modified Title").withCompany("Google")
                .withAddress("Chapaeva, 5").withHome("02-82456-5").withMobile("123-581-58").withWork("789-42-892")
                .withFax("489-42-4858").withEmail("modtest@mail.com").withEmail2("modtest2@mail.com").withEmail3("modtest3@mail.com")
                .withHomepage("127.0.0.1").withBday("28").withBmonth("January").withByear("2087").withFirstname("Petr").withAday("10")
                .withAmonth("June").withAyear("1921").withAddress2("Shtirlitza street, 87").withNotes("Modified notes").withPhone2("56-00-978");

        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
