import asserts.AssertWithLog;
import components.courses.CourseCardHeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CoursesRootPage;
import webdriver.WebDriverFactory;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class Courses_Test {

    private static final Logger logger = LogManager.getLogger(Courses_Test.class);
    private WebDriver driver;
    private final WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private CoursesRootPage coursesRootPage = null;

    List tmp = List.of("1", "2");

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "chrome").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);

        coursesRootPage = new CoursesRootPage(driver);
        coursesRootPage.openPage();
        coursesRootPage.killFilthyPopups();
        coursesRootPage.chooseCoursesType();
        coursesRootPage.getCoursesCardsListComponent().getSearchLoader();

    }

    @Test
    @DisplayName("Проверка количества курсов в разделе «Тестирование»")
    public void testCoursesList() {


        int counter = coursesRootPage.countCardsInList();
        coursesRootPage.hrefsOfCardsInList();
        assertWithLog.assertWithLog(counter == 10, "курсов на странице = " + counter);
    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов (прокликивая)")
    public void testCoursesCard2() {

        coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();
        //SoftAssertions softly = new SoftAssertions();
        assertAll(
                () ->
                        coursesRootPage.openLinksInNewTabAndCollectTexts().forEach((checkTitle, checkContent) ->
                                assertWithLog.assertWithLog(checkContent.length() > 1, checkTitle) )
        );

        //assertThat(coursesRootPage.openLinksInNewTabAndCollectTexts()).;
        //softly.assertAll();
    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов (по ссылкам)")
    public void testCoursesCard() {
       // courseCardPage = new CourseCardPage(driver);


        coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();
         /*
        Так развернем список полностью...
        ...но там дальше курс в старой вёрстке...
        ...можно сделать ещё один класс для старой страницы, и применить тут ооп как раз (с) Картушин
        */
        coursesRootPage.hrefsOfCardsInList().forEach(
                thisCard -> {
                    driver.get(thisCard);

                    CourseCardHeaderComponent courseCardHeaderComponent = new CourseCardHeaderComponent(driver);

                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getTitle()),
                            thisCard + " заголовок"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getDescr()),
                            thisCard + " описание"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getDuration()),
                            thisCard + " длительность"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getFormat()),
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
