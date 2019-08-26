package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            app.contact().create(new ContactData().withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("AgentSmith")
                    .withTitle("Some Title").withCompany("The Matrix").withAddress("K. Marksa street, 18")
                    .withHome("01-123456-07").withMobile("02-11-223-01").withWork("14-256-01-28")
                    .withFax("214-15-78-268").withEmail("test@mail.com").withEmail2("test2@mail.com").withEmail3("test3@mail.com")
                    .withHomepage("localhost").withBday("5").withBmonth("March").withByear("2047").withFirstname("Ivan").withAday("6")
                    .withAmonth("May").withAyear("2017").withAddress2("Lenina street, 35").withNotes("Some notes").withPhone2("35-18-29")
                    .inGroup(groups.iterator().next()), true);
            app.goTo().homePage();
        }
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test 1").withFooter("footer").withHeader("header"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddToGroup(){
        Groups groups = app.db().groups();
        Groups groupsWithoutContacts = app.db().groups();
        app.contact().selectAllGroups();
        ContactData addContact = app.db().contacts().iterator().next();
        if (addContact.getGroups().size() > 0) {
            for(GroupData group : addContact.getGroups()) {
                groupsWithoutContacts.remove(group);
            }
            if (groupsWithoutContacts.size() == 0) {
                app.contact().selectGroupInFooter(groups.iterator().next().getId());
                app.contact().removeContactFromGroup(addContact);
                app.goTo().homePage();
                app.contact().selectAllGroups();
                groupsWithoutContacts.add(groups.iterator().next());
            }
        }
        Groups before = app.db().contact(addContact.getId()).getGroups();
        System.out.println(before);
        ContactData contact = new ContactData().withId(addContact.getId()).withFirstname(addContact.getFirstname())
                .withAddress(addContact.getAddress()).withLastname(addContact.getLastname());
        app.contact().selectGroupById(groupsWithoutContacts.iterator().next().getId());
        app.contact().addContactToGroup(contact);
        app.goTo().homePage();
        app.contact().selectAllGroups();
        assertThat(app.db().contact(contact.getId()).getGroups().size(), equalTo(before.size() + 1));
        Groups after = app.db().contact(contact.getId()).getGroups();
        System.out.println(after);
        assertThat(after, equalTo(before.withAdded(groupsWithoutContacts.iterator().next())));
    }
}
