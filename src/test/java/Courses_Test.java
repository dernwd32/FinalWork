import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import waiters.StandartWaiter;
import webdriver.WebDriverFactory;

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
        String webDriverName = System.getProperty("browser", "chrome").toLowerCase();
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
    @DisplayName("Проверка фильтров и списка карточек курсов")
    public void testCoursesList() {


        int counter = coursesRootPage.countCardsInList();
        coursesRootPage.hrefsOfCardsInList();
        assertWithLog.assertWithLog(counter == 10, "курсов на странице = " + counter);
    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов")
    public void testCoursesCard() {
        courseCardPage = new CourseCardPage(driver);


        coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();
         /*
        Так развернем список полностью...
        ...но там дальше курс в старой вёрстке...
        ...можно сделать ещё один класс для старой страницы, и применить тут ооп как раз (с) Картушин
        */
        coursesRootPage.hrefsOfCardsInList().forEach(
       // coursesRootPage.cardsInList().forEach(
                thisCard -> {
                    if (!thisCard.equals("https://otus.ru/online/manualtesting")) { //заглушка для старой страницы
                        driver.get(thisCard);
                        //courseCardPage.killFilthyPopups(); //тут не мешают
                        // thisCard.click();

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

//                    coursesRootPage.openPage();
//                    coursesRootPage.chooseCoursesType();
//                    coursesRootPage.getCoursesCardsListComponent().getSearchLoader();
                    }
                }
        );
        // softly.assertAll();



    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
