package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Example4 {
    static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    String urlServer = "http://192.168.1.2:4444/wd/hub";
    DesiredCapabilities dc;
    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
        dc.setBrowserName("firefox");
//        WebDriver driver = new RemoteWebDriver(new URL(urlServer), dc);
//        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
//        threadLocalDriver.set(driver);
    }

    @Test(enabled = true)
    public void mainTest2() throws MalformedURLException, InterruptedException {
//        WebDriver driver = threadLocalDriver.get();
        WebDriver driver = new RemoteWebDriver(new URL(urlServer), dc);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("pranjle4u@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("shopata");
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(40000);
        System.out.println(driver.getTitle());
        driver.manage().deleteAllCookies();
        driver.quit();

    }

    @Test(enabled = true)
    public void mainTest1() throws InterruptedException, MalformedURLException {
//        WebDriver driver = threadLocalDriver.get();
        Thread.sleep(20000);
        WebDriver driver = new RemoteWebDriver(new URL(urlServer), dc);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("pranjle4u@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("shopata");
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
//        threadLocalDriver.get().quit();

    }
}
