import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import waiters.StandartWaiter;
import webdriver.WebDriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class First_Test {


    private static final Logger logger = LogManager.getLogger(First_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    AssertWithLog assertWithLog = null;
    CoursesRootPage coursesRootPage = null;
    CourseCardPage courseCardPage = null;
    StandartWaiter standartWaiter = null;


    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "chrome").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);
        standartWaiter = new StandartWaiter(driver);

        coursesRootPage = new CoursesRootPage(driver);
        coursesRootPage.openPage();
        coursesRootPage.getCoursesTypesMenuComponent().chooseCoursesType();
        coursesRootPage.getCoursesCardsListComponent().killFilthyPopups();

    }

    @Test
    public void testCoursesList() {
        int counter = coursesRootPage.getCoursesCardsListComponent().countCardsInList();
        assertWithLog.assertWithLog(counter == 10, "курсов на странице = " + counter);
    }

    @Test
    public void testCoursesCard() {
        //не осилил, надо закрыть попапы + вывести все курсы
        //coursesRootPage.getCoursesCardsListComponent().clickShowMoreWhileUCan();

     //   List<WebElement> cardsList = coursesRootPage.getCoursesCardsListComponent().cardsInList();
//        cardsList.forEach(
//                card -> {
//                    card.click();
//
//                }
//        );


        courseCardPage = new CourseCardPage(driver);

        coursesRootPage.getCoursesCardsListComponent().hrefsOfCardsInList().forEach(
                thisCard -> {
                    driver.get(thisCard);
                    //standartWaiter.waitForTextMatches(courseCardPage.getCourseCardHeaderComponent().getSubHeaderCourseDurationXPath(), "")
                    //courseCardPage.getCourseCardHeaderComponent().
                    assertWithLog.assertWithLog(
                            courseCardPage.getCourseCardHeaderComponent().isntEmpty(
                                    courseCardPage
                                    .getCourseCardHeaderComponent()
                                    .getH1TitleXPath()),
                            "h1"
                    );
                }
        );




        //int counter = coursesRootPage.getCoursesCardsListComponent().countCardsInList();
        //System.out.println(counter);
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
