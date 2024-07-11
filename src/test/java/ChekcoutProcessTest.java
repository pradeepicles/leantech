import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.WebElements.WE_CheckoutProcessTest;
import org.example.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChekcoutProcessTest extends Base {

    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public void startTest() {
            report = new ExtentReports();
            test = report.createTest("ExtendReport");
    }

    @BeforeTest
    public void getBrowserAndURL() throws IOException {
        driverStart(getpropertyfile("Assignment1_URL").getProperty("Assignment1_URL"));
    }

    @Test
    public void checkoutProcessForThreeItems() {
        WE_CheckoutProcessTest element1 = PageFactory.initElements(driver, WE_CheckoutProcessTest.class);
        try {
            enterText(element1.we_inputUsername, getpropertyfile("Assignment1_URL").getProperty("Username"));
            enterText(element1.we_inputPassword, getpropertyfile("Assignment1_URL").getProperty("Password"));
            clickbutton(element1.we_btnLogin);
            List<String> productLists = new ArrayList<>();
            productLists.add("Sauce Labs Backpack");
            productLists.add("Sauce Labs Bike Light");
            productLists.add("Sauce Labs Bolt T-Shirt");

            List<WebElement> lstWebELementsn = findElements(element1.we_productList);
            for (WebElement e : lstWebELementsn) {
                if (productLists.contains(e.getText())) {
                    By addToCartButton = By.xpath(String.format(element1.we_addToCart, e.getText()));
                    clickbutton(addToCartButton);
                }
            }
            clickbutton(element1.we_cartItems);
            clickbutton(element1.we_btnCheckout);
            enterText(element1.we_inputFirstName, "ABC");
            enterText(element1.we_inputLastName, "XYZ");
            enterText(element1.we_inputzipCode, "500124");
            clickbutton(element1.we_btnContinue);
            clickbutton(element1.we_btnFinish);
            String getText = getText(element1.we_txtOderComplete);
            Assert.assertEquals(getText, "Thank you for your order!");
            takeScreenshot("Test001");

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @AfterTest
    public void driverClose() {
        driverEnd();
    }

    @AfterClass
    public static void endTest()
    {
        report.removeTest(test);
        report.flush();
    }
}
