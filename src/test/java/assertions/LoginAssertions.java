package assertions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAssertions {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginAssertions(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(40));
	}
	
	public boolean isHomePagedisplayed() {
		WebElement homelocated=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome ']")));
		String readText=homelocated.getText();
		System.out.println(readText);
		return homelocated.isDisplayed();

	}
	
	//to validate error message for invalid login
		public boolean isinvaliderrorDisplayed() {
			WebElement invalid_err=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Login failed')]")));
			String readErr=invalid_err.getText();
			System.out.println(readErr);
			return invalid_err.isDisplayed();
		}

}
