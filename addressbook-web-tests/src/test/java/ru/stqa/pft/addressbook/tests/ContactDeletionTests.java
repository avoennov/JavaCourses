package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com", "localhost", "5", "March", "2047", "Ivan", "6", "May", "2017", "test1","Lenina street, 35", "Some notes", "35-18-29"), true);
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
    }
}
