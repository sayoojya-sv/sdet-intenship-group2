package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignViewPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public SignViewPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	}
	
	public void click_signview() throws InterruptedException {
		//to click signview page
		WebElement sign_view=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Sign/View MoU']")));
		sign_view.click();
		
		//to view action
		WebElement viewPage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='View'])[1]")));
		viewPage.click();
		
		// to redirect back to signview page
		WebElement back_opt=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Back']")));
		back_opt.click();
		Thread.sleep(500);
		
		//to redirect back to homepage
		WebElement back_option=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Back']")));
		back_option.click();
	}

}
