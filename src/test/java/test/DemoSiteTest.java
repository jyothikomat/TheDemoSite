package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pages.Adduser;
import pages.Homepage;
import pages.Login;

public class DemoSiteTest {
	static WebDriver driver;
	// String URL="https://www.google.com/";
	
	@BeforeClass
	public static void init(){
	
		System.setProperty("webdriver.chrome.driver","src/test/resources/CromeDriver/chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().fullscreen();
	}
	@Before
	public  void setup()
	{
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(Homepage.getUrl());
	}
	
	
	@AfterClass
	public static void  teardown()
	{
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
	
		
	Homepage nav =PageFactory.initElements(driver, Homepage.class);
	Adduser add =PageFactory.initElements(driver, Adduser.class);
	Login log =PageFactory.initElements(driver, Login.class);
	
	//Navigate to "Add a user"
	
	nav.addUser();
	Thread.sleep(3000);
	add.signUp("test", "test1");
	Thread.sleep(3000);
	//nav.navLogin();
	
	//Navigate to "Login"
	
	nav.navLogin();
	Thread.sleep(3000);
	log.signIn("test", "test1");
	Thread.sleep(3000);
	assertEquals("**Successful Login**",driver.findElement(By.xpath("//b[contains(text(),'**Successful Login**')]")).getText());
	Thread.sleep(3000);
		
	}

}
