package loginTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;

public class ToVerifyHomePageTest extends BaseClass {
	
	@Test
	public void toVerifyHomePageTest() throws EncryptedDocumentException, IOException {
		String expectedTitle = eUtility.fetchDataFromExcelSheets("Sheet2", 1, 0);

		wUtils.waitForTitle(driver, "Enter");

		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("PASS: The title is Verified");
		
	}

}
