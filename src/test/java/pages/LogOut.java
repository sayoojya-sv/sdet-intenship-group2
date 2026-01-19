package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LogOut(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	}
	
	//to click logout
	public void log_out() {
		
		WebElement log=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dr.John ']")));
		log.click();
		
		WebElement log_clk=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Sign Out']")));
		log_clk.click();
	}
	

}
