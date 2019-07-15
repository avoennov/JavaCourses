package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.SelectContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("middlename"), contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNickname());
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

    public void submitContactModification() {
      click(By.xpath("//input[@name='update']"));
    }

    public void openEditor() {
      click(By.xpath("//img[@alt='Edit']"));
    }

    public void deleteSelectedContact() {
      click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
    }
}
