package pages;

import common.AbstractCommon;
import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;


public abstract class AbstractPage extends AbstractCommon {
    protected static final String BASE_URL = System.getProperty("base.url");
    protected String pageUrl;

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }


    protected AbstractPage(WebDriver driver){
        super(driver);
    }


    public void openPage() {
        driver.get(BASE_URL + pageUrl);
    }

}
