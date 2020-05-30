package com.java.juiceshop;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class UI_JuiceShopTest {

    String email = "b@gmail.com";
    String password = "abc@gmail.com";

    @Test
    public void registerUser(){
        WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
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
