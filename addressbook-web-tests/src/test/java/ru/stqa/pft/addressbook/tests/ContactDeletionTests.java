package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.SelectContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact(new SelectContactData("3"));
        app.getContactHelper().deleteSelectedContact();
    }
}
