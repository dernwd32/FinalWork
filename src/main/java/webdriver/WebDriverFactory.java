package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class WebDriverFactory implements IWebDriver{
    private WebDriver driver;

    @Override
    public WebDriver create(String webDriverName) {
        return create(webDriverName, "");
    }

    @Override
    public WebDriver create(String webDriverName, String mode)  {

        boolean argsWasSet = (!mode.isEmpty() && mode.charAt(0) == '-');

        switch (webDriverName) {

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                //Если начинается с дефиса - передаём в options браузера, иначе - через driver.manage
                if (argsWasSet) options.addArguments(mode);
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                WebDriverManager.chromiumdriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (argsWasSet) options.addArguments(mode);
                driver = new ChromeDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if (argsWasSet) options.addArguments(mode);
                driver = new EdgeDriver(options);
            }
            default ->
               throw new RuntimeException(String.format("Browser <%s> is not supported by the factory", webDriverName));


        }

        //режимы, передаваемые в driver.manage без "-" в начале
        if (mode.equals("maximize"))  driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(DEFAULT_IMPLICITLY_DURATION));

        return driver;
    }
}
