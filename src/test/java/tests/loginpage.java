package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;



public class loginpage extends Base {


    @Test (dataProvider = "mydata")
    public void logindata(String user , String pass){
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginbutton = driver.findElement(By.id("login-button"));

        username.clear();
        password.clear();
        username.sendKeys(user);
        password.sendKeys(pass);
        loginbutton.click();


        if(user.equals("standard_user")&pass.equals("secret_sauce")) {
            WebElement logo = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
            System.out.println("Valid login attempt with user: " + user);
            Assert.assertTrue(logo.isDisplayed(), "We are in Homepage");
        }
        else {
            WebElement error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
            System.out.println("Invalid login attempt with user: " + user + " and pass: " + pass);
            Assert.assertTrue(error.isDisplayed(),"Error message displayed");
        }

    }


    @DataProvider
   public Object[][] mydata(){
        return new Object[][] {
                {"standard_user", "secret_sauce"}, // Valid username and password
                {"sam", "samer"}, // Invalid username and password
                {"", ""}, // Empty username and password
                {"", "secret_sauce"}, // Empty username and valid password
                {"standard_user", ""} // Valid username and empty password
        };
}
}
