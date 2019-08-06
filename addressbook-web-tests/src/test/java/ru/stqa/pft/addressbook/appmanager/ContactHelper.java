package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.SelectContactData;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("middlename"), contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNickname());
      attach(By.name("photo"), contactData.getPhoto());
      type(By.name("title"), contactData.getTitle());
      type(By.name("company"), contactData.getCompany());
      type(By.name("address"), contactData.getAddress());
      type(By.name("home"), contactData.getHome());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("work"), contactData.getWork());
      type(By.name("fax"), contactData.getFax());
      type(By.name("email"), contactData.getEmail());
      type(By.name("email2"), contactData.getEmail2());
      type(By.name("email3"), contactData.getEmail3());
      type(By.name("homepage"), contactData.getHomepage());
      selectFromList(By.name("bday"), contactData.getBday());
      selectFromList(By.name("bmonth"), contactData.getBmonth());
      type(By.name("byear"), contactData.getByear());
      selectFromList(By.name("aday"), contactData.getAday());
      selectFromList(By.name("amonth"), contactData.getAmonth());
      type(By.name("ayear"), contactData.getAyear());
      if (creation) {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      type(By.name("address2"), contactData.getAddress2());
      type(By.name("notes"), contactData.getNotes());
      type(By.name("phone2"), contactData.getPhone2());
    }

    public void returnToHomePage() {
      click(By.linkText("home page"));
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(SelectContactData selectContactData) {
      selectCheckbox(selectContactData.getId());
    }

    public void selectFirstContact() {
      click(By.name("selected[]"));
    }

    public void selectFirstContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactModification() {
      click(By.xpath("//input[@name='update']"));
    }

    public void openEditor(int id) {
      wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
      click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation){
      initContactCreation();
      fillContactForm(contact, creation);
      submitContactCreation();
      returnToHomePage();
    }

    public void modify(ContactData contact) {
        openEditor(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectFirstContactById(contact.getId());
        deleteSelectedContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            String allphones = element.findElement(By.xpath("./td[6]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allemails = element.findElement(By.xpath("./td[5]")).getText();
            contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname)
                    .withAddress(address).withAllPhones(allphones).withAllEmails(allemails));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        openEditor(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withHome(home).withMobile(mobile).withWork(work).withEmail(email1)
                .withEmail2(email2).withEmail3(email3);
    }
}
