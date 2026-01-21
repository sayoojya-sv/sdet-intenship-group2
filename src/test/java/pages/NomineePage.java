package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NomineePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public NomineePage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(40));
	}
	//to click nominee page
	public void click_nominee() {
		WebElement nom=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Nominate Faculty/Student']")));
		nom.click();
	}
	
	//to click nomination history
	public void nom_history() {
		WebElement nomineeH=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Nomination History']")));
		nomineeH.click();
		
		//to view details
		WebElement nom_view=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='View Details'])[1]")));
		nom_view.click();
		
		//to click close button
		WebElement nom_close=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']")));
		nom_close.click();
		
	}
	
	//to click nominee-page
	public void nomineE() {
		WebElement nomine=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Nominations']")));
		nomine.click();
	}
	
	//to register in nominee page
	public void nominee_register() {
		WebElement reg=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Register Now']")));
		reg.click();
	}
	
	//to upload the csv file for bul-registration
	public void upload_csv_file() {
	    WebElement up_load = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

	    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", up_load);
	    up_load.sendKeys("C:\\Users\\sayoojya\\OneDrive\\Desktop\\SDETCourse\\ICTAK_INTERNSHIP\\ictak_internship\\src\\test\\resources\\intern-data.csv");
	    
	    
	   //to  click upload button 
	    WebElement upload_clk=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload']")));
	    upload_clk.click();
	    
	}
	
	//to click pay 
	public void click_pay() {
		WebElement pay=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='8550']")));
		pay.click();
	
	//to click confirm pay
		WebElement con_firm=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm']")));
		con_firm.click();
	}
	
	//to click payment method
	public void pay_method() {
		WebElement pay_ment=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Netbanking'])[1]")));
		pay_ment.click();
		
	}
	
	//to select banking type
	public void sel_bank() {
		// Locate dropdown
		WebElement bank_list = driver.findElement(By.id("netbanking-select"));

		// Select bank by visible text
		Select selectBank = new Select(bank_list);
		selectBank.selectByVisibleText("Avenues Test for New TC");
		
	}
	
	//to confirm pay
	public void confirm_pay() throws InterruptedException {
		WebElement pay_m=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='make-payment-net-banking'])[1]")));
		pay_m.click();
		Thread.sleep(500);
		
		WebElement ok_btn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='OK']")));
		ok_btn.click();
		
	}
	
	
	
	//to redirect back to nominee page
//	public void redirect_nomineepage() {
//		WebElement red_irect=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Back')]")));
//		red_irect.click();
//	}
//	
	//to redirect back to home page
		public void redirect_homepage() {
			WebElement red=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Back')]")));
			red.click();
			
		}

}
