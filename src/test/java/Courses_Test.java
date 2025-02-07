import asserts.AssertWithLog;
import components.courses.AbstractCourseCardHeaderComponent;
import components.courses.CourseCardHeaderComponent;
import components.courses.CourseOldCardHeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import webdriver.WebDriverFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class Courses_Test {

    private static final Logger logger = LogManager.getLogger(Courses_Test.class);
    private WebDriver driver;
    private final WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private CoursesRootPage coursesRootPage = null;
    private CourseCardPage courseCardPage = null;

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

                    AbstractCourseCardHeaderComponent courseCardHeaderComponent = null;
                    if (Objects.equals(driver.getCurrentUrl(), "https://otus.ru/online/manualtesting"))
                        courseCardHeaderComponent = new CourseOldCardHeaderComponent(driver);
                    else
                        courseCardHeaderComponent = new CourseCardHeaderComponent(driver);

                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getH1Title()),
                            thisCard + " заголовок"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getDescrSubtitle()),
                            thisCard + " описание"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getSubHeaderCourseDuration()),
                            thisCard + " длительность"
                    );
                    assertWithLog.assertWithLog(
                            courseCardHeaderComponent.isntEmpty(
                                    courseCardHeaderComponent
                                            .getSubHeaderCourseFormat()),
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
