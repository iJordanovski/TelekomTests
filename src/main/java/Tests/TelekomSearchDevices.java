package Tests;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TelekomSearchDevices {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	 WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void testTelekomSearchDevices() throws Exception {
    driver.get("https://www.telekom.mk/");
    driver.findElement(By.xpath("//div[@id='header']/div/div[3]/div/div/div[2]/a[3]/div")).click();
    driver.findElement(By.id("qr")).click();
    driver.findElement(By.id("qr")).clear();
    driver.findElement(By.id("qr")).sendKeys("Iphone");
    driver.findElement(By.xpath("//div[@id='header']/div/div[3]/div/div/div[2]/a[3]/div")).click();

   Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"ctl00_ctl00_ctl00_cmscontent_CmsMainONE_search1\"]/div[1]/ul/li[1]/div/div[2]/a/h2")).getText().contains("IPHONE"));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	Thread.sleep(2000);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
