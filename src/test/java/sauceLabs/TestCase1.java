package sauceLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCase1 {

    public WebDriver driver;
    public static final String USERNAME="karimunnisa";
    public static final String ACCESS_KEY="43e81af1-637a-4ffc-b416-1821fe399b06";
    public static final String URL="https://"+USERNAME+":"+ACCESS_KEY+"@ondemand.saucelabs.com:443/wd/hub";

    @BeforeClass
    @Parameters({"browser","version","platform"})
    public  void setUp(String br, String vr, String pf) throws Exception
    {
        DesiredCapabilities desiredCapabilities= DesiredCapabilities.chrome();
        desiredCapabilities.setCapability("browserName",br);
        desiredCapabilities.setCapability("version",vr);
        desiredCapabilities.setCapability("platform",pf);
        desiredCapabilities.setCapability("name","OrangeHRMTest");
        desiredCapabilities.setCapability("extendedDebugging","true"); // to get network logs

        driver=new RemoteWebDriver(new java.net.URL(URL),desiredCapabilities);
    }

 @Test
 public void verifyLogin()
 {
     driver.get("https://opensource-demo.orangehrmlive.com/");
     Assert.assertEquals(driver.getTitle(),"OrangeHRM");

     driver.findElement(By.id("txtUsername")).sendKeys("Admin");
     driver.findElement(By.id("txtPassword")).sendKeys("admin123");
     driver.findElement(By.id("btnLogin")).click();

     Assert.assertEquals(driver.getTitle(),"OrangeHRM");
 }

 @AfterClass
    public void tearDown()
 {
     driver.quit();
 }

}
