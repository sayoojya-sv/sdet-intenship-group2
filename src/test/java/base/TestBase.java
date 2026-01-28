package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
	
	protected WebDriver driver;
	Properties prop;
	
	//read the config property file
	public void readProp() throws IOException {
		FileReader f1=new FileReader("C:\\Users\\sayoojya\\OneDrive\\Desktop\\SDETCourse\\ICTAK_INTERNSHIP\\ictak_internship\\src\\test\\resources\\config.properties");
		prop=new Properties();
		prop.load(f1);
	}
	
	@BeforeClass
	public void setUp() throws IOException {
		readProp();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
