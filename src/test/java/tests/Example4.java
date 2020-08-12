package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Example3 {
    WebDriver driver;
    DriverService ds;
    ChromeOptions chromeOptions;
    boolean gridEnabled = true;
    String urlServer = "http://192.168.1.2:4444/wd/hub";

    @BeforeTest
    public void beforeTestMethod() throws IOException {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        if (!gridEnabled) {
            ds = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("./resources/server/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            ds.start();
        }
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        if (!gridEnabled)
            driver = new RemoteWebDriver(ds.getUrl(), chromeOptions);
        else
            driver = new RemoteWebDriver(new URL(urlServer), chromeOptions);
    }

    @Test
    public void mainTest1() throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("automation basics");
        System.out.println(driver.getTitle());
        System.out.println("Total Links : " + driver.findElements(By.tagName("a")).size());
        System.out.println(((RemoteWebDriver)driver).getSessionId());
        Thread.sleep(178000);
    }

    @Test
    public void mainTest2() {
        driver.get("https://www.amazon.in/");
        System.out.println(driver.getTitle());
        System.out.println("Total Links : " + driver.findElements(By.tagName("a")).size());
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();

    }

    @AfterTest
    public void afterTestMethod() {
        if (!gridEnabled)
            ds.stop();
    }
}
