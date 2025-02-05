import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import waiters.StandartWaiter;
import webdriver.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertAll;


public class Courses_Test {

    private static final Logger logger = LogManager.getLogger(Courses_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    AssertWithLog assertWithLog = null;
    CoursesRootPage coursesRootPage = null;
    CourseCardPage courseCardPage = null;
    StandartWaiter standartWaiter = null;
    //    SoftAssertions softly = new SoftAssertions();

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "firefox").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);
        standartWaiter = new StandartWaiter(driver);

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
    @DisplayName("Проверка содержимого карточек курсов (по ссылкам)")
    public void testCoursesCard() {
        courseCardPage = new CourseCardPage(driver);


        coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();
         /*
        Так развернем список полностью...
        ...но там дальше курс в старой вёрстке...
        ...можно сделать ещё один класс для старой страницы, и применить тут ооп как раз (с) Картушин
        */
        coursesRootPage.hrefsOfCardsInList().forEach(
                thisCard -> {
                    if (!thisCard.equals("https://otus.ru/online/manualtesting")) { //заглушка для старой страницы
                        driver.get(thisCard);
                        assertWithLog.assertWithLog(
                                courseCardPage.getCourseCardHeaderComponent().isntEmpty(
                                        courseCardPage
                                                .getCourseCardHeaderComponent()
                                                .getH1Title()),
                                thisCard + " заголовок"
                        );
                        assertWithLog.assertWithLog(
                                courseCardPage.getCourseCardHeaderComponent().isntEmpty(
                                        courseCardPage
                                                .getCourseCardHeaderComponent()
                                                .getDescrSubtitle()),
                                thisCard + " описание"
                        );
                        assertWithLog.assertWithLog(
                                courseCardPage.getCourseCardHeaderComponent().isntEmpty(
                                        courseCardPage
                                                .getCourseCardHeaderComponent()
                                                .getSubHeaderCourseDuration()),
                                thisCard + " длительность"
                        );
                        assertWithLog.assertWithLog(
                                courseCardPage.getCourseCardHeaderComponent().isntEmpty(
                                        courseCardPage
                                                .getCourseCardHeaderComponent()
                                                .getSubHeaderCourseFormat()),
                                thisCard + " формат"
                        );

                    }
                }
        );
        // softly.assertAll();
    }


    @Test
    @DisplayName("Проверка содержимого карточек курсов (прокликивая)")
    public void testCoursesCard2() {

        coursesRootPage = new CoursesRootPage(driver);
        //coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();

        assertAll(
                () ->
                        coursesRootPage.openLinksInNewTabAndCollectTexts().forEach((checkTitle, checkContent) -> {
                            assertWithLog.assertWithLog(
                                    checkContent.length() > 1,
                                    checkTitle
                            );
                        })
        );

    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
