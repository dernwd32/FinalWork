package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class StandartWaiter implements WaiterInt {

  private WebDriver driver = null;

  public StandartWaiter(WebDriver driver) {
    this.driver = driver;
  }


  public boolean waitForCondition(ExpectedCondition condition) {
    return waitForCondition( condition, DEFAULT_WAITER_TIMEOUT);
  }

  @Override
  public boolean waitForCondition(ExpectedCondition condition, long timeout) {
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(timeout));
    try {
      webDriverWait.until(condition);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public boolean waitForElementVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }

  public boolean waitForElementLocatedAndVisible(By... locators) {
    return waitForElementLocatedAndVisible(10000, locators);
  }
  public boolean waitForElementLocatedAndVisible(long timeout, By... locators) {
    for (By locator : locators) {
      if (!waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator), timeout)) {
        return false;
      }
    }
      return true;
  }

  public boolean waitForElementNotVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.invisibilityOf(element));
  }

  public boolean waitToBeClickable(WebElement element) {
    return waitForCondition(ExpectedConditions.elementToBeClickable(element));
  }

}