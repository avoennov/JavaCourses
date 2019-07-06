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

    wd.findElement(By.linkText("add new")).click();
    wd.findElement(By.name("firstname")).sendKeys("Ivan");
    wd.findElement(By.name("middlename")).sendKeys("Ivanovich");
    wd.findElement(By.name("lastname")).sendKeys("Ivanov");
    wd.findElement(By.name("nickname")).sendKeys("AgentSmith");
    wd.findElement(By.name("title")).sendKeys("Some Title");
    wd.findElement(By.name("company")).sendKeys("The Matrix");
    wd.findElement(By.name("address")).sendKeys("K. Marksa street, 18");
    wd.findElement(By.name("home")).sendKeys("01-123456-07");
    wd.findElement(By.name("mobile")).sendKeys("02-11-223-01");
    wd.findElement(By.name("work")).sendKeys("14-256-01-28");
    wd.findElement(By.name("fax")).sendKeys("214-15-78-268");
    wd.findElement(By.name("email")).sendKeys("test@mail.com");
    wd.findElement(By.name("email2")).sendKeys("test2@mail.com");
    wd.findElement(By.name("email3")).sendKeys("test3@mail.com");
    wd.findElement(By.name("homepage")).sendKeys("localhost");
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("5");
    wd.findElement(By.name("bday")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("March");
    wd.findElement(By.name("bmonth")).click();
    wd.findElement(By.name("byear")).sendKeys("2047");
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("6");
    wd.findElement(By.name("aday")).click();
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("May");
    wd.findElement(By.name("amonth")).click();
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys("2017");
/*    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");*/
    wd.findElement(By.name("new_group")).click();
    wd.findElement(By.name("address2")).sendKeys("Lenina street, 35");
    wd.findElement(By.name("theform")).click();
    wd.findElement(By.name("notes")).sendKeys("Some notes");
    wd.findElement(By.name("phone2")).sendKeys("35-18-29");
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    wd.findElement(By.linkText("home page")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
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
