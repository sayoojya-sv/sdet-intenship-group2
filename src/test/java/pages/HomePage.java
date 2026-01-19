package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	}
	
	//to click institution request
	public void institution_request() {
		WebElement inst=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Institution Requests']")));
		inst.click();
	}
	
	//to go back to home page
	public void back_home() {
		WebElement back_to_hme=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class,'block')])[1]")));
		back_to_hme.click();
	}
	
	
}
