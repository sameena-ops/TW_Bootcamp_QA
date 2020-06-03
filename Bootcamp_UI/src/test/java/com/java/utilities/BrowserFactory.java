package com.java.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    // instance of singleton class

    public static WebDriver driver= null;

  public static void getInstance(String browser){
      if(driver == null){
          if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
          }

      else {
          WebDriverManager.chromedriver().setup();
          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setCapability("browserName", "chrome");
          try {
              driver = new RemoteWebDriver(new URL("http://hub.com:4444/wd/hub"),
                      capabilities);
          } catch (MalformedURLException e) {
              e.printStackTrace();
          }
          }

          driver.manage().window().maximize();
      }

  }

  public  static void quit(){
      System.out.println("Quit the browser");
      driver.quit();
      driver=null;
  }
}
