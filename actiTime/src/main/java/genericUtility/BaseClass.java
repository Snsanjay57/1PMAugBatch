package genericUtility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import POMRepo.HomePage;
import POMRepo.LoginPage;

public class BaseClass {
	
	public WebDriver driver;
	public FileUtility fUtility = new FileUtility();
    public ExcelUtility eUtility = new ExcelUtility();
	public WebDriverUtility wUtils = new WebDriverUtility();
	
	@BeforeSuite
	public void bsConfig() {
		System.out.println("Connect to DataBase");
	}
	
	@BeforeClass
	public void bcConfig() throws IOException {
		String url = fUtility.fetchDataFromPropertyFile("url");
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");	
		driver = new ChromeDriver();		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		
	}
	
	@BeforeMethod
	public void bmConfig() throws IOException {
		String username = fUtility.fetchDataFromPropertyFile("username");
		String password  = fUtility.fetchDataFromPropertyFile("password");
		
		LoginPage login = new LoginPage(driver);
		login.loginAction(username, password);
		
	}
	
	@AfterMethod
	public void amConfig() {
		HomePage home = new HomePage(driver);
		home.logoutAction();
	}

	@AfterClass
	public void acConfig() {
		driver.quit();
	}
	
	@AfterSuite
	public void asConfig() {
		System.out.println("Data base is closed");
	}
}
