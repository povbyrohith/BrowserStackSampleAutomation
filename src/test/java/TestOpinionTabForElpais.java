import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePage;
import pages.HomePage;
import utils.InitializeDriver;

public class TestOpinionTabForElpais extends InitializeDriver {
    WebDriver driver;
    HomePage home;
    ArticlePage article;
    InitializeDriver init = new InitializeDriver();
    @BeforeMethod
    public void initDriver() {
        driver = init.setupDriver();
        home = new HomePage(driver);
        article = new ArticlePage(driver);
    }

    @Test
    public void testHeadersInOpinionTab() {
        home.clickOnAcceptCookiesButton();
        home.clickOnOpinionTab();
        article.printArticleHeaderAndContent();
        article.printRepeatedWordsInHeaders();
    }

    @AfterMethod
    public void quitDriver() {
        init.quitDriver();
    }
}
