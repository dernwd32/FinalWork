package asserts;

import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AssertWithLog {
    private WebDriver driver = null;
    private Logger logger = null;
    private SoftAssertions softly = null;

    //конструктор с передачей текущего драйвера и логгера
    public AssertWithLog(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    //конструктор с передачей softly для корректной обработки заранее неизвестного кол-ва софтассёртов (в цикле)
    public AssertWithLog(SoftAssertions softly, WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.softly = softly;
    }

    // Метод принимающий только условие, автоматически вычисляющий всё остальное.
    // Работает с конструктором AssertWithLog(WebDriver driver, ILog ILog)
    public void assertWithLog(boolean condition) {

        // если не передано значение message, получаем StackTrace для того, чтоб узнать имя тестового метода,
        // вызывающего assertWithLog
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        assertWithLog(
                condition,
                //по дефолту отправляем в message название тестового метода, из которого вызван текущий assertWithLog
                stackTrace[2].getMethodName()
        );
    }

    // Метод принимающий условие и сообщение, автоматически вычисляющий всё остальное
    // Работает с конструктором AssertWithLog(WebDriver driver, ILog ILog)
    public void assertWithLog(boolean condition, String message) {

       //если не указан браузер в контексте, получаем его имя через capabilities
       String currentBrowser = Objects.isNull(System.getProperty("browser"))
                            ? ((RemoteWebDriver) driver).getCapabilities().getBrowserName()
                            : System.getProperty("browser");

        message = String.format("%-125s",
                        String.format("%-18s", "[" + currentBrowser + "]")
                        + "-> "
                        + message
                  );

        String messagePass = message + " > PASS";
        String messageFail = message + " > FAIL";

        if (condition) logger.info(messagePass);
        else logger.error(messageFail);


        if (!Objects.isNull(softly))
            softly.assertThat(condition).as(message).isTrue();
        else
            assertTrue(condition, message);
    }


}
