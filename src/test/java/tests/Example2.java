package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Example2 {
    WebDriver driver;
    DriverService ds;
    ChromeOptions chromeOptions;

    @BeforeTest
    public void beforeTestMethod() throws IOException {
        ds = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("./resources/server/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ds.start();

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver = new RemoteWebDriver(ds.getUrl(), chromeOptions);
    }

    @Test
    public void mainTest1() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("automation basics");
        System.out.println(driver.getTitle());
        System.out.println("Total Links : " + driver.findElements(By.tagName("a")).size());
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
        ds.stop();
    }
}
