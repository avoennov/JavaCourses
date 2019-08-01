package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com", "localhost", "5", "March", "2047", "Ivan", "6", "May", "2017", "test1","Lenina street, 35", "Some notes", "35-18-29"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectFirstContact(before.size() - 1);
        app.getContactHelper().openEditor();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Petrovich", "Petrov", "Neo", "Modified Title", "Google", "Chapaeva, 5", "02-82456-5", "123-581-58", "789-42-892", "489-42-4858", "modtest@mail.com", "modtest2@mail.com", "modtest3@mail.com", "127.0.0.1", "28", "January", "2087", "Petr", "10", "June", "1921", null, "Shtirlitza street, 87", "Modified notes", "56-00-978");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
