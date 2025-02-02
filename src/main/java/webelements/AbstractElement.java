package webelements;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;

public class AbstractElement {

    protected WebDriver driver = null;
    protected StandartWaiter standartWaiter = null;
    protected Faker faker = new Faker();

    public AbstractElement(WebDriver driver){
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }
}
