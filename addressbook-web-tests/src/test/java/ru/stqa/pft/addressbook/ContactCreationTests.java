package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  @Test
  public void testContactCreationTests() throws Exception {
    gotoContactPage();
    fillContactForm("Ivan", "Ivanovich", "Ivanov", "AgentSmith", "Some Title", "The Matrix", "K. Marksa street, 18", "01-123456-07", "02-11-223-01", "14-256-01-28", "214-15-78-268", "test@mail.com", "test2@mail.com", "test3@mail.com", "localhost", "5", "March", "2047", "6", "May", "2017", "Lenina street, 35", "Some notes", "35-18-29");
    submitContactCreation();
    returnToHomePage();
  }

  private void fillContactForm(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String bday, String bmonth, String byear, String aday, String amonth, String ayear, String address2, String notes, String phone2) {
    wd.findElement(By.name("firstname")).sendKeys(firstname);
    wd.findElement(By.name("middlename")).sendKeys(middlename);
    wd.findElement(By.name("lastname")).sendKeys(lastname);
    wd.findElement(By.name("nickname")).sendKeys(nickname);
    wd.findElement(By.name("title")).sendKeys(title);
    wd.findElement(By.name("company")).sendKeys(company);
    wd.findElement(By.name("address")).sendKeys(address);
    wd.findElement(By.name("home")).sendKeys(home);
    wd.findElement(By.name("mobile")).sendKeys(mobile);
    wd.findElement(By.name("work")).sendKeys(work);
    wd.findElement(By.name("fax")).sendKeys(fax);
    wd.findElement(By.name("email")).sendKeys(email);
    wd.findElement(By.name("email2")).sendKeys(email2);
    wd.findElement(By.name("email3")).sendKeys(email3);
    wd.findElement(By.name("homepage")).sendKeys(homepage);
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bday);
    wd.findElement(By.name("bday")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bmonth);
    wd.findElement(By.name("bmonth")).click();
    wd.findElement(By.name("byear")).sendKeys(byear);
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(aday);
    wd.findElement(By.name("aday")).click();
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(amonth);
    wd.findElement(By.name("amonth")).click();
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys(ayear);
/*    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");*/
    wd.findElement(By.name("new_group")).click();
    wd.findElement(By.name("address2")).sendKeys(address2);
    wd.findElement(By.name("theform")).click();
    wd.findElement(By.name("notes")).sendKeys(notes);
    wd.findElement(By.name("phone2")).sendKeys(phone2);
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void gotoContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    logout();
    wd.quit();
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
