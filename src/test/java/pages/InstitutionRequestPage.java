package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InstitutionRequestPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public InstitutionRequestPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(40));
	}
	
	//to click institution request
		public void institution_request() {
			WebElement inst=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Institution Requests']")));
			inst.click();
		}
		
		public void actions_click() throws InterruptedException {
			//to view on all request
			
			WebElement all_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='All']")));
			all_req.click();
			Thread.sleep(1000);
			//to view pending request
			
			WebElement pend_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Pending']")));
			pend_req.click();
			Thread.sleep(1000);
			
			//to view in-progress request
			
			WebElement in_progress_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='In Progress']")));
			in_progress_req.click();
			Thread.sleep(1000);
			
			
			//to view completed request
			
			WebElement completed_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Completed']")));
			completed_req.click();
		}
		
		//to click create request
		public void createReq() {
			WebElement cr_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create Request']")));
			cr_req.click();
		}
		
		//to fill the data of create request
		
		public void setData(String namE,String eMail,String messagE) throws InterruptedException {
			
		    //to select request-type
			WebElement req_type=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[option[text()='Program']]")));
			Select s1=new Select(req_type);
			s1.selectByVisibleText("Program");
			Thread.sleep(500);
			
			//to select program
			WebElement prgm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[option[text()='Select Program']]")));
			Select s2=new Select(prgm);
			s2.selectByVisibleText("Python Programming");
			Thread.sleep(500);
			
			//to enter name
			WebElement nam=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your name']")));
			nam.sendKeys(namE);
			Thread.sleep(500);
			
			//to enter email
			WebElement e_mail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your email']")));
			e_mail.sendKeys(eMail);
			Thread.sleep(500);
			
			//to enter message
			WebElement e_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Enter your message']")));
			e_msg.sendKeys(messagE);
			Thread.sleep(500);
			
			//to click submit button
			WebElement sub_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
			sub_btn.click();
		}
		
		public void allReq() throws InterruptedException {
			WebElement all_req=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='All']")));
			all_req.click();
//			Thread.sleep(1000);
		}
		
		public void redirect_home() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
	
			WebElement hme=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/app/institution']")));
			js.executeScript("arguments[0].click();", hme);
		}

}
