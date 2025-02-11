import asserts.AssertWithLog;
import components.courses.CourseCardHeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CoursesRootPage;
import webdriver.WebDriverFactory;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class Courses_Test {

    private static final Logger logger = LogManager.getLogger(Courses_Test.class);
    private WebDriver driver;
    private final WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private CoursesRootPage coursesPage = null;

    List tmp = List.of("1", "2");

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "firefox").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);

        coursesPage = PageFactory.initElements(driver, CoursesRootPage.class);
        coursesPage.openPage();
        coursesPage.killFilthyPopups();
        coursesPage.chooseCoursesType();
        coursesPage.getCardsList().getSearchLoader();

    }

    @Test
    @DisplayName("Проверка количества курсов в разделе «Тестирование»")
    public void testCoursesList() {
        int counter = coursesPage.countCardsInList();
        coursesPage.hrefsOfCardsInList();
        assertWithLog.assertWithLog(counter == 10, "курсов на странице = " + counter);
    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов (прокликивая)")
    public void testCoursesCard2() {

        coursesPage.getCardsList().clickShowMoreWhileUCan();

        assertAll(
                () ->
                        coursesPage.openLinksInNewTabAndCollectTexts().forEach((checkTitle, checkContent) ->
                                assertWithLog.assertWithLog(checkContent.length() > 1, checkTitle) )
        );

    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов (по ссылкам)")
    public void testCoursesCard() {
       // courseCardPage = new CourseCardPage(driver);


        coursesPage.getCardsList().clickShowMoreWhileUCan();

        coursesPage.hrefsOfCardsInList().forEach(
                thisCard -> {
                    driver.get(thisCard);

                    CourseCardHeaderComponent cardHeader = new CourseCardHeaderComponent(driver);

                    assertWithLog.assertWithLog(
                            cardHeader.getTitle().getText().length() > 1,
                            thisCard + " заголовок"
                    );
                    assertWithLog.assertWithLog(
                            cardHeader.getDescr().getText().length() > 1,
                            thisCard + " описание"
                    );
                    assertWithLog.assertWithLog(
                            cardHeader.getDuration().getText().length() > 1,
                            thisCard + " длительность"
                    );
                    assertWithLog.assertWithLog(
                            cardHeader.getFormat().getText().length() > 1,
                            thisCard + " формат"
                    );


                }
        );
        // softly.assertAll();
    }



    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
