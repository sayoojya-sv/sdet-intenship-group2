package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import assertions.HomePageAssertions;
import assertions.LoginAssertions;
import base.TestBase;
import pages.FaqPage;
import pages.HomePage;
import pages.InstitutionRequestPage;
import pages.LogOut;
import pages.LoginPage;
import pages.NomineePage;
import pages.SignViewPage;

public class TestPage extends TestBase{
	
	LoginPage log;
	HomePage hom;
	InstitutionRequestPage irp;
	SignViewPage svp;
	NomineePage np;
	FaqPage faq;
	LogOut log_p;
	LoginAssertions log_assert;
	HomePageAssertions hom_assert;
	
	@BeforeClass
	public void objInit() {
		log=new LoginPage(driver);
		hom=new HomePage(driver);
		irp=new InstitutionRequestPage(driver);
		svp=new SignViewPage(driver);
		np=new NomineePage(driver);
		faq=new FaqPage(driver);
		log_p=new LogOut(driver);
		log_assert=new LoginAssertions(driver);
		hom_assert=new HomePageAssertions(driver);
		
	}
	
	@Test(priority=1)
	public void loginTest() {
		log.emailLog("developer.05@ictkerala.org");
		log.passLog("Test@9632");
		log.logIn();
		Assert.assertTrue(log_assert.isHomePagedisplayed());
	}
	
	@Test(priority=2)
	public void homePageTest() throws InterruptedException {
		hom.institution_request();
		Assert.assertTrue(hom_assert.isinstreqidDisplayed());
		hom.back_home();
	}
	
	@Test(priority=3)
	public void institutionRequestTest() throws InterruptedException {
		irp.institution_request();
		irp.actions_click();
		irp.createReq();
		irp.setData("Anu", "anu@gmail.com","Python");
		irp.allReq();
		Assert.assertTrue(hom_assert.isreqCreated());
	}
	@Test(priority=4)
	public void signviewTest() throws InterruptedException {
		irp.redirect_home();
		svp.click_signview();
	}
	
	@Test(priority=5)
	public void nomineeTest() throws InterruptedException {
		
		np.click_nominee();
		Assert.assertTrue(hom_assert.isnomineeDisplayed());
		np.nom_history();
		np.nomineE();
//		np.nominee_register();
//		np.upload_csv_file();
//		np.click_pay();
//		np.pay_method();
//		np.sel_bank();
//		np.confirm_pay();
		np.redirect_homepage();
	}
	
	@Test(priority=6)
	public void faqTest() {
		faq.click_faq();
		faq.redirect_hom();
	}
	
	@Test(priority=7)
	public void logoutTest() {
		log_p.log_out();
	}
	
	@Test(priority=8)
	public void InvalidloginTest() {
		log.emailLog("developer@ictkerala.org");
		log.passLog("Test@963");
		log.logIn();
		Assert.assertTrue(log_assert.isinvaliderrorDisplayed());
	}

}
