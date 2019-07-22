package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com", "localhost", "5", "March", "2047", "Ivan", "6", "May", "2017", "test1","Lenina street, 35", "Some notes", "35-18-29"), true);
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().openEditor();
        app.getContactHelper().fillContactForm(new ContactData("Petrovich", "Petrov", "Neo", "Modified Title", "Google", "Chapaeva, 5", "02-82456-5", "123-581-58", "789-42-892", "489-42-4858", "modtest@mail.com", "modtest2@mail.com", "modtest3@mail.com", "127.0.0.1", "28", "January", "2087", "Petr", "10", "June", "1921", null, "Shtirlitza street, 87", "Modified notes", "56-00-978"), false);
        app.getContactHelper().submitContactModification();
    }
}
