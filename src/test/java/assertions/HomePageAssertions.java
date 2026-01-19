package assertions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageAssertions {
	WebDriver driver;
	WebDriverWait wait;
	
	//to initialize drivers
	public HomePageAssertions(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(80));
	}
	
	//to check whether the page is navigated to institution request
	public boolean isinstreqidDisplayed() {
		WebElement ins_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Institution Requests']")));
		return ins_msg.isDisplayed();
	}
	
	//to check whether the request is created
	
	public boolean isreqCreated() {
		WebElement req_crt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[text()='test'])[1]")));
		return req_crt.isDisplayed();
	}
	
	//to check whether nominee page displayed
	
	public boolean isnomineeDisplayed() {
		WebElement nominEe=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Nominations']")));
		return nominEe.isDisplayed();
	}
	

}
