package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.SelectContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact(new SelectContactData("2"));
        app.getContactHelper().openEditor();
        app.getContactHelper().fillContactForm(new ContactData("Petrovich", "Petrov", "Neo", "Modified Title", "Google", "Chapaeva, 5", "02-82456-5", "123-581-58", "789-42-892", "489-42-4858", "modtest@mail.com", "modtest2@mail.com", "modtest3@mail.com", "127.0.0.1", "28", "January", "2087", "Petr", "10", "June", "1921", "Shtirlitza street, 87", "Modified notes", "56-00-978"));
        app.getContactHelper().submitContactModification();
    }
}
