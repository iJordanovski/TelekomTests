package Tests;


import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  WebDriverManager.chromedriver().setup();
	   driver = new ChromeDriver();
  }

  //Fail Login by Wrong username or password
  @Test
  public void testLogin() throws Exception {
    driver.get("https://www.telekom.mk/");
    driver.findElement(By.xpath("//div[@id='loginNameMove']/a/span[2]")).click();
    driver.findElement(By.name("UserName")).click();
    driver.findElement(By.name("UserName")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | name=UserName | ]]
    driver.findElement(By.name("UserName")).click();
    driver.findElement(By.name("UserName")).clear();
    driver.findElement(By.name("UserName")).sendKeys("xxx");
    driver.findElement(By.xpath("//div[2]/div")).click();
    driver.findElement(By.name("Password")).clear();
    driver.findElement(By.name("Password")).sendKeys("123");
    driver.findElement(By.id("btnLogin")).click();
    driver.findElement(By.xpath("//li")).click();
    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/div/form/fieldset/div[1]/ul/li")).getText().contains("Невалидно корисничко име или лозинка."));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	 Thread.sleep(3000);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
