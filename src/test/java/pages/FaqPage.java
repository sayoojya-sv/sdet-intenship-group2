package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaqPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public FaqPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	}
	
	//click faq page
	public void click_faq() {
		WebElement faqclk=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='FAQ']")));
		faqclk.click();
	}
	
	//to redirect to homepage
	public void redirect_hom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement hmpg=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/app/institution']")));
		js.executeScript("arguments[0].click();", hmpg);
	}

}
