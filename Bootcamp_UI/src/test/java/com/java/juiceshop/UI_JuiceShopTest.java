package com.java.juiceshop;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UI_JuiceShopTest {

    String email = "b@gmail.com";
    String password = "abc@gmail.com";
    WebDriver driver;
    @Test
    public void registerUser(){
        WebDriverManager.chromedriver().setup();


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
     //driver.get("http://localhost:3000/#/");
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/#/register");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.id("emailControl")).sendKeys(email);
        driver.findElement(By.id("passwordControl")).sendKeys(password);
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.name("securityQuestion")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.id("registerButton")).click();
    }
}
