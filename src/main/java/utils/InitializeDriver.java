package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class InitializeDriver {
    protected WebDriver driver = null;
    public WebDriver setupDriver() {
        try {
            // Load properties
            Properties properties = new Properties();
            FileInputStream configFile = new FileInputStream("src/main/resources/config.properties");
            properties.load(configFile);
            String environment = properties.getProperty("environment");
            String browser = properties.getProperty("browser");

            if (environment.equalsIgnoreCase("local")) {
                // Local execution
                System.setProperty("webdriver.chrome.driver", "/Users/rohith.kolli/Downloads/chromedriver-mac-x64/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            } else if (environment.equalsIgnoreCase("browserstack")) {
                // BrowserStack execution
                String username = properties.getProperty("browserstack.username");
                String accessKey = properties.getProperty("browserstack.accessKey");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-infobars");  // Example argument

                URL browserStackURL = new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub");
                driver = new RemoteWebDriver(browserStackURL, options);
            }

            // Test actions
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.get("https://elpais.com");
            System.out.println("Page title is: " + driver.getTitle());
            return driver;

        } catch (Exception e) {
            System.out.println("Unable to initialize driver");
            return null;
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
