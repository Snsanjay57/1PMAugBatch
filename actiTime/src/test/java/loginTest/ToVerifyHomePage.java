package loginTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import POMRepo.HomePage;
import POMRepo.LoginPage;
import genericUtility.ExcelUtility;
import genericUtility.FileUtility;

public class ToVerifyHomePage {
	public static void main(String[] args) throws IOException {
		
		//Converting physical representation of property file to java representation.	
		FileUtility fUtility = new FileUtility();
		String url = fUtility.fetchDataFromPropertyFile("url");
		String username = fUtility.fetchDataFromPropertyFile("username");
		String password  = fUtility.fetchDataFromPropertyFile("password");
		
		ExcelUtility eUtility = new ExcelUtility();
		String expectedTitle = eUtility.fetchDataFromExcelSheets("Sheet2", 1, 0);
		
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");	
		WebDriver driver = new ChromeDriver();		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.loginAction(username, password);
		
		WebDriverWait wait = new WebDriverWait(driver, 20); //Explecite wait
		wait.until(ExpectedConditions.titleContains("Enter"));
		
		String actualTitle = driver.getTitle();
		
		System.out.println(actualTitle);
		System.out.println(expectedTitle);
		
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("PASS: The title is Verified");
		
//		if(actualTitle.equals(expectedTitle))
//		{
//			System.out.println("PASS: The Home page is verified");
//		}
//		else
//			System.out.println("PASS: The Home page is verified");

		HomePage home = new HomePage(driver);
		home.logoutAction();
		
		driver.quit();
	}

}
