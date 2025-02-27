package pages;

import asserts.AssertWithLog;
import components.courses.AbstractCourseCardHeaderComponent;
import components.courses.CourseCardHeaderComponent;

import components.courses.CourseOldCardHeaderComponent;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CourseCardPage extends AbstractPage{
    //String pageUrl = BASE_URL + "/lessons/$1/";

    public CourseCardPage(WebDriver driver) {
        super(driver);
    }



    private String title;
    private String descr;
    private String duration;
    private String format;
    private String url;

    public CourseCardPage(WebDriver driver, String title, String descr, String duration, String format, String url) {
        super(driver);
        this.title = title;
        this.descr = descr;
        this.duration = duration;
        this.format = format;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public String getDescr() {
        return descr;
    }
    public String getDuration() {
        return duration;
    }
    public String getFormat() {
        return format;
    }
    public String getUrl() {
        return url;
    }

    AbstractCourseCardHeaderComponent courseCardHeaderComponent = null;

    public List<CourseCardPage> getCardsHeaders(Set<String> links) {
        List<CourseCardPage> courses = new ArrayList<>();

        for (String thisUrl : links) {
            Document doc;
            try {
                doc = Jsoup.connect(thisUrl).get();

                //если не найден такой див, значит страница в старой вёрстке, иначе в новой
                if (doc.select("div.galmep").isEmpty())
                    courseCardHeaderComponent = new CourseOldCardHeaderComponent(driver);
                else
                    courseCardHeaderComponent = new CourseCardHeaderComponent(driver);

                CourseCardPage thisCard = new CourseCardPage(
                        driver,
                        doc.selectXpath(courseCardHeaderComponent.getTitleStrLoc())
                                .text(),
                        doc.selectXpath(courseCardHeaderComponent.getDescrStrLoc())
                                .text(),
                        doc.selectXpath(courseCardHeaderComponent.getDurationStrLoc())
                                .text(),
                        doc.selectXpath(courseCardHeaderComponent.getFormatStrLoc())
                                .text(),
                        thisUrl
                );

                courses.add(thisCard);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return courses;
    }

    public void assertCardsHeaders(List<CourseCardPage> courses, Logger logger) {
        AssertWithLog assertWithLog = new AssertWithLog(driver, logger);
        courses.forEach(thisCard -> {
            assertWithLog.assertWithLog(
                    thisCard.getTitle().length() > 1,
                    thisCard.getUrl() + " заголовок"
            );
            assertWithLog.assertWithLog(
                    thisCard.getDescr().length() > 1,
                    thisCard.getUrl() + " описание"
            );
            assertWithLog.assertWithLog(
                    thisCard.getDuration().length() > 1,
                    thisCard.getUrl() + " длительность"
            );
            assertWithLog.assertWithLog(
                    thisCard.getFormat().length() > 1,
                    thisCard.getUrl() + " формат"
            );
        });
    }
}
