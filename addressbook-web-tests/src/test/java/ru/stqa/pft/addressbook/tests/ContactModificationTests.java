package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
/*            app.contact().create(new ContactData(
                    "Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix",
                    "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28",
                    "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com",
                    "localhost", "5", "March", "2047", "Ivan", "6",
                    "May", "2017", "test1","Lenina street, 35", "Some notes",
                    "35-18-29"), true);*/

            app.contact().create(new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith").withTitle("Some Title").withCompany("The Matrix")
                    .withAddress("K. Marksa street, 18").withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
                    .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
                    .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
                    .withAmonth("May").withAyear("2017").withGroup("test1").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29"), true);
        }
    }

    @Test(enabled = true)
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
/*        ContactData contact = new ContactData(before.get(index).getId(), "Petrovich", "Petrov",
                "Neo", "Modified Title", "Google", "Chapaeva, 5", "02-82456-5",
                "123-581-58", "789-42-892", "489-42-4858", "modtest@mail.com", "modtest2@mail.com",
                "modtest3@mail.com", "127.0.0.1", "28", "January", "2087",
                "Petr", "10", "June", "1921", null, "Shtirlitza street, 87",
                "Modified notes", "56-00-978");*/

        ContactData contact = new ContactData().withId(before.get(index).getId()).withMiddlename("Petrovich").withLastname("Petrov")
                .withNickname("Neo").withTitle("Modified Title").withCompany("Google")
                .withAddress("Chapaeva, 5").withHome("02-82456-5").withMobile("123-581-58").withWork("789-42-892")
                .withFax("489-42-4858").withEmail("modtest@mail.com").withEmail2("modtest2@mail.com").withEmail3("modtest3@mail.com")
                .withHomepage("127.0.0.1").withBday("28").withBmonth("January").withByear("2087").withFirstname("Petr").withAday("10")
                .withAmonth("June").withAyear("1921").withAddress2("Shtirlitza street, 87").withNotes("Modified notes").withPhone2("56-00-978");

        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
