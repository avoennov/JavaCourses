package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData("Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com", "localhost", "5", "March", "2047", "Ivan", "6", "May", "2017", "test1","Lenina street, 35", "Some notes", "35-18-29");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
