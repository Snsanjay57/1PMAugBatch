package POMRepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToGenerateStaleElementReferenceException {

	public static void main(String[] args) {

		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");	
		WebDriver driver = new ChromeDriver();		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.co.in/");
		
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Vikasbeta");
		
		driver.navigate().refresh();
		
		element.sendKeys("sdfdsv");
		
		
	}

}
