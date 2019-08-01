package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
/*    ContactData contact = new ContactData(
            "Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix",
            "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28",
            "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com",
            "localhost", "5", "March", "2047", "Ivan", "6",
            "May", "2017", "test1","Lenina street, 35", "Some notes", "35-18-29");*/

    ContactData contact = new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith").withTitle("Some Title").withCompany("The Matrix")
            .withAddress("K. Marksa street, 18").withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
            .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
            .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
            .withAmonth("May").withAyear("2017").withGroup("test1").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29");

    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
