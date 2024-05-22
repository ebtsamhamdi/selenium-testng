package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Homepage extends Base {
    private WebElement icon;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    private void intial(){
        login();
        icon =driver.findElement(By.id("react-burger-menu-btn"));
    }


    @Test (priority = 1)
    public void addtocart() {
        WebElement addbutton0 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement addbutton1 = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addbutton0.click();
        addbutton1.click();

        WebElement cartbadgenum = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        WebElement removebutton0 =driver.findElement(By.id("remove-sauce-labs-backpack"));
        WebElement removebutton1 = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        softAssert.assertTrue(cartbadgenum.isDisplayed(), "items added");
        softAssert.assertTrue(removebutton0.isDisplayed(),"button0 clicked");
        softAssert.assertTrue(removebutton1.isDisplayed(),"button1 clicked");
        System.out.println("num of items displayed in cart logo");
    }

    @Test (priority = 2)
    public void about_from_humburgericon (){

        WebElement about =driver.findElement(By.id("about_sidebar_link"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        icon.click();
        about.click();


        WebElement image = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div/div[1]/div[1]/a/div[1]/span/img"));
        String actual = driver.getCurrentUrl();
        String expected = ("https://saucelabs.com/");
        System.out.println("Actual URL="+ actual);
        System.out.println("Expected URL =" + expected);

        Assert.assertEquals(actual,expected);
        Assert.assertTrue(image.isDisplayed(),"passed");
        System.out.println("About page opened successfully");

    }

    @Test(priority = 3)
    public void Resetappstate_from_humburgericon () throws IOException {

        addtocart();
        icon.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement resetbutton =driver.findElement(By.id("reset_sidebar_link"));
        WebElement resetremovebutton1=driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-fleece-jacket\"]"));
        WebElement resetremovebutton0=driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        resetbutton.click();

        softAssert.assertTrue(resetremovebutton0.isSelected(),"Reset App state was faild");
        System.out.println("Faild-Remove button 0 still selected");
        softAssert.assertTrue(resetremovebutton1.isSelected(),"Reset App state was faild");
        System.out.println("Faild-Remove button 1 still selected");
        File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile,new File("C:\\Users\\ebtsam\\IdeaProjects\\untitled\\screenshot\\reset.png"));

    }


    @Test(priority = 4)
    public void logout(){
        icon.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement logout =driver.findElement(By.id("logout_sidebar_link"));
        logout.click();
        String expected ="https://www.saucedemo.com/";
        String actual = driver.getCurrentUrl();
        System.out.println(actual);
        softAssert.assertEquals(actual,expected);
        System.out.println("logout process pass");


    }

}