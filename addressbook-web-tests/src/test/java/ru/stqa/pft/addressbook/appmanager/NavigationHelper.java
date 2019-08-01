package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
      if (isElementPresent(By.tagName("h1"))
              && wd.findElement(By.tagName("h1")).getText().equals("Groups")
              && isElementPresent(By.name("new"))) {
          return;
      }
      click(By.linkText("groups"));
    }

    public void gotoContactPage() {
      if (isElementPresent(By.tagName("h1"))
              && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
              && isElementPresent(By.name("Enter"))) {
          return;
      }
      click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        if (isElementPresent(By.cssSelector("strong"))
                && wd.findElement(By.tagName("strong")).getText().equals("Number of results: ")
                && isElementPresent(By.name("searchstring"))) {
            return;
        }
        click(By.linkText("home"));
    }
}
