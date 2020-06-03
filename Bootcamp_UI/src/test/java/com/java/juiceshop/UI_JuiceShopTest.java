package com.java.juiceshop;
import com.java.utilities.BrowserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UI_JuiceShopTest {

    String email = RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
    String password = "abc@gmail.com";
    //  WebDriver driver;

    @BeforeEach
    public void setup(){
        BrowserFactory.getInstance("remoteChrome");
    }

    @Test
    public void testFirstTimeRegister() {
        BrowserFactory.driver.get("http://juice-shop:3000/#/register");
        BrowserFactory.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try{
        if (BrowserFactory.driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]")).isDisplayed()) {
            BrowserFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            BrowserFactory.driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")).click();
        }
        }
        catch (NoSuchElementException e){
        BrowserFactory.driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        BrowserFactory.driver.findElement(By.id("emailControl")).sendKeys(email);
        BrowserFactory.driver.findElement(By.id("passwordControl")).sendKeys(password);
        BrowserFactory.driver.findElement(By.id("repeatPasswordControl")).sendKeys(password);
        BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        BrowserFactory.driver.findElement(By.name("securityQuestion")).click();
        BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        BrowserFactory.driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
        BrowserFactory.driver.findElement(By.id("securityAnswerControl")).sendKeys(password);
        BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        BrowserFactory.driver.findElement(By.id("registerButton")).click();
        boolean bol = BrowserFactory.driver.findElement(By.id("email")).isDisplayed();
        Assertions.assertTrue(bol);
    }
    }

    @Test
    public void testRegisteredUserLogin() {
        //setup();
        BrowserFactory.driver.manage().window().maximize();
        BrowserFactory.driver.get("http://juice-shop:3000/#/login");
        BrowserFactory.driver.findElement(By.id("navbarAccount")).click();
        BrowserFactory.driver.findElement(By.id("navbarLoginButton")).click();
        BrowserFactory.driver.findElement(By.id("email")).sendKeys("b@gmail.com");
        BrowserFactory.driver.findElement(By.id("password")).sendKeys("abc@gmail.com");
        BrowserFactory.driver.findElement(By.id("loginButton")).click();
        BrowserFactory.driver.findElement(By.id("navbarAccount")).click();
        Boolean logout_btn = BrowserFactory.driver.findElement(By.xpath("//*[@id=\"navbarLogoutButton\"]")).isDisplayed();
        Assertions.assertTrue(logout_btn);
        BrowserFactory.driver.findElement(By.xpath("//*[@id=\"navbarLogoutButton\"]")).click();
    }

    @Test
    public void testWriteReview() {
        // setup();
        BrowserFactory.driver.manage().window().maximize();
        BrowserFactory.driver.get("http://juice-shop:3000/#/");
        BrowserFactory.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        try {
            if (BrowserFactory.driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]")).isDisplayed()) {
                BrowserFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                BrowserFactory.driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")).click();
            }
        } catch (NoSuchElementException e) {
            BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            BrowserFactory.driver.findElement(By.id("navbarAccount")).click();
            BrowserFactory.driver.findElement(By.id("navbarLoginButton")).click();
            BrowserFactory.driver.findElement(By.id("email")).sendKeys("b@gmail.com");
            BrowserFactory.driver.findElement(By.id("password")).sendKeys("abc@gmail.com");
            BrowserFactory.driver.findElement(By.id("loginButton")).click();
            BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            BrowserFactory.driver.findElement(By.xpath("//div[@class='table-container custom-slate']/div[2]/mat-grid-list/div/mat-grid-tile[1]")).click();
            BrowserFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            // new WebDriverWait(BrowserFactory.driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'mat-input-')]"))).click();
           // BrowserFactory.driver.findElement(By.id("mat-dialog-1")).click();
            BrowserFactory.driver.findElement(By.xpath("//textarea[contains(@id,\"mat-input\")]")).click();
            BrowserFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            BrowserFactory.driver.findElement(By.xpath("//textarea[contains(@id,\"mat-input\")]")).sendKeys("awesome");
            BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            BrowserFactory.driver.findElement(By.id("submitButton")).click();
            BrowserFactory.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            BrowserFactory.driver.navigate().refresh();
           // BrowserFactory.driver.findElement(By.xpath("//*[@id=\"mat-dialog-1\"]/app-product-details/mat-dialog-content/div/div[2]/mat-dialog-actions/button[1]")).click();
        }

    }
    @After
    public void quit(){
        BrowserFactory.driver.quit();
    }

    }
