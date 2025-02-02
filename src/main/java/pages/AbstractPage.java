package pages;

import annotations.ComponentBlueprint;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;

public abstract class AbstractPage {
    String BASE_URL = System.getProperty("base.url");

    abstract void openPage();

    protected WebDriver driver = null;
    protected StandartWaiter standartWaiter = null;
   // protected Faker faker = new Faker();

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }

}
