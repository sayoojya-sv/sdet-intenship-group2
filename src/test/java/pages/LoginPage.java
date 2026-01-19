package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	//to enter emailid
	public void emailLog(String mailid) {
		WebElement em_ail=wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		em_ail.sendKeys(mailid);
	}
	//to enter password
	public void passLog(String passw) {
		WebElement pass_word=wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		pass_word.sendKeys(passw);
	}
	
	//to click sign in
	public void logIn() {
		WebElement log=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign In']")));
		log.click();
	}


	
	

}
