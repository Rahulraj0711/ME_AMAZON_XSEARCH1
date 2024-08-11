
package apTests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

//Selenium Imports
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
///
import org.openqa.selenium.support.ui.Select;

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test Case: testCase01");
        driver.get("https://www.amazon.in/");
        if (driver.getCurrentUrl().contains("amazon")) {
            System.out.println("End Test Case: testCase01 | Passed");
        } else {
            System.out.println("End Test Case: testCase01 | Failed");
        }
    }

    public void testCase02() {
        System.out.println("Start Test Case: testCase02");
        driver.get("https://www.amazon.in/");
        WebElement searchBox = driver.findElement(By.cssSelector("input#twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        driver.findElement(By.cssSelector("input#nav-search-submit-button")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.cssSelector("div.s-list-col-right div div div h2"));
        boolean result = false;
        for (WebElement item : searchList) {
            if (item.getText().toLowerCase().contains("laptop")) {
                result = true;
                break;
            }
        }
        if (result) {
            System.out.println("End Test Case: testCase02 | Passed");
        } else {
            System.out.println("End Test Case: testCase02 | Failed");
        }
    }

    public void testCase03() {
        System.out.println("Start Test Case: testCase03");
        driver.get("https://www.amazon.in/");
        driver.findElement(By.partialLinkText("Electronics")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String url = driver.getCurrentUrl();
        if (url.contains("electronics")) {
            System.out.println("End Test Case: testCase03 | Passed");
        } else {
            System.out.println("End Test Case: testCase03 | Failed");
        }
    }

}
