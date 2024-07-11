package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static java.lang.System.getProperty;

public class Base {
    public WebDriver driver;
    Properties prop;

    public void driverStart(String URL) {

        try {
            prop = readPropertiesFile();
            String BrowserName = prop.getProperty("Browser").toUpperCase();


            switch (BrowserName) {
                case "CHROME" -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                }
                case "IE" -> {
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                }
                case "FIREFOX" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default -> System.out.println("Browser intialization not configured");
            }

            driver.manage().window().maximize();
            driver.get(URL);

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void driverEnd() {
        driver.close();
    }

    public static Properties readPropertiesFile() {
        Properties file1 = null;
        try {
            FileInputStream file = new FileInputStream("C:\\LeanTech\\src\\main\\java\\org\\example\\configProperties\\config.properties");
            file1 = new Properties();
            file1.load(file);

        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return file1;
    }

    public Properties getpropertyfile(String value) throws IOException {
        Properties p = new Properties();
        InputStream stream = new FileInputStream("C:\\LeanTech\\src\\main\\java\\org\\example\\configProperties\\config.properties");
        p.load(stream);
        p.getProperty(value);
        return p;

    }

    public void enterText(By locator, String text) {
        if (text == null || text.equalsIgnoreCase("")) {
            System.out.println("No text to enter in " + locator);
        } else {
            try {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            } catch (Exception e) {
                System.out.println(locator + "Cannot locate element");
                e.fillInStackTrace();
            }
        }
    }

    public void clickbutton(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void takeScreenshot(String imageName) {
        try {
            File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Calendar calender = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
            SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyy");
            String callerName = new Exception().getStackTrace()[1].getClassName();
            String imageLocation = getProperty("user.dir") + "\\src\\main\\java\\org\\example\\screenshots\\";
            String todt = format1.format(calender.getTime());
            boolean f2 = new File(imageLocation + todt + "\\").mkdir();
            boolean f1 = new File(imageLocation + todt + "\\" + callerName + "\\").mkdir();
            String actualImage = imageLocation + todt + "\\" + callerName + "\\" + imageName + "_" + format.format(calender.getTime()) + ".png";
            File descFile = new File(actualImage);
            FileHandler.copy(image, descFile);

        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
