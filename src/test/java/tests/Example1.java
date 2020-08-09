package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

//        Using browser specific classes like ChromeDriver, InternetExplorerDriver, FirefoxDriver
public class Example1 {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "./resources/server/chromedriver.exe");

        driver = new ChromeDriver();

        /* 1. This creates a overhead while creating and terminating the driver instance.
         *   For every test run,
         *       Server process is started then driver is created
         *       driver is destroyed and then server process is terminated.
         */
        /* 2. Not scalable - Can run tests in local system only.
         */
        /* Solution :-
         *  Use RemoteWebDriver Class.
         */
        driver.manage().window().maximize();
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
}
