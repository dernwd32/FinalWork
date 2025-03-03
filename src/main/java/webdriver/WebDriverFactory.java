package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.net.HttpURLConnection;


public class WebDriverFactory implements IWebDriver{
    private WebDriver driver;

    //http://193.104.57.173/wd/hub
    private String remoteUrl = System.getProperty("remote.url");
    private String browserName = System.getProperty("browser.name", "chrome");
    private String browserVersion = System.getProperty("browser.version", "128.0");


    private URL urlChecker(String thisUrl) {
        if (Objects.isNull(thisUrl)) return null;
        try {
            URL testUrl = new URL(thisUrl);
            HttpURLConnection connection = (HttpURLConnection) testUrl.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            // Проверяем, что код ответа в диапазоне 200-399 (успешные ответы)
            if (responseCode >= 200 && responseCode < 400) return testUrl;
        } catch (Exception e) {
            throw new RuntimeException("Cannot open selenoid url: " + thisUrl
                    + " \n" + e);
        }
        return null;
    }

    @Override
    public WebDriver create(String webDriverName) {
        return create(webDriverName, "");
    }

    @Override
    public WebDriver create(String webDriverName, String mode)  {

        URL checkedRemoteUrl = urlChecker(remoteUrl);

        if (!Objects.isNull(checkedRemoteUrl)) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
            Map<String, Object> options = new HashMap<>();
            options.put(CapabilityType.BROWSER_VERSION, browserVersion);
            options.put("enableVNC", true);
            capabilities.setCapability("selenoid:options", options);
            return new RemoteWebDriver(checkedRemoteUrl, capabilities);
        }

        boolean argsWasSet = (!mode.isEmpty() && mode.charAt(0) == '-');

        switch (webDriverName.toLowerCase().trim()) {

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                //Если начинается с дефиса - передаём в options браузера, иначе - через driver.manage
                if (argsWasSet) options.addArguments(mode);
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
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
