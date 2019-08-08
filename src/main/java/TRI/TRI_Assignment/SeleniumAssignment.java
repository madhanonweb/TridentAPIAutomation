package TRI.TRI_Assignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumAssignment {
	
		WebDriver driver;
		
		WebElement TextBox;
		
		String TextboxValue=null;
		
		String number =null;
		
		String TabData=null;
		
		String TabString =null;
		
		int TabAPI=0;
		
		int count=0;
		
		@Test
		public void SeleniumTest() throws InterruptedException
		{
			
			
		 	System.setProperty("webdriver.gecko.driver", "./Resources/geckodriver.exe");
			
	//	System.setProperty("webdriver.chrome.driver", "./Resources/chromedriver.exe");
			
			//driver= new ChromeDriver();
		 	
		 	driver= new FirefoxDriver();
			
			driver.get("https://www.programmableweb.com/category/open-data/api");
			
		//	driver.findElement(By.xpath("//*[@id=\"edit-term--2\"]"))
			
			TextBox= driver.findElement(By.xpath("//*[@id=\"edit-term--2\"]"));
			
			TextboxValue= TextBox.getAttribute("placeholder");
			
			System.out.println("value of textbox===>"+ TextboxValue);			
						
		
			
			number=TextboxValue.replaceAll("[^0-9]", "");
			
			System.out.println("‘Programmableweb has " + number + " APIs’");
			
			List<WebElement> Tab= driver.findElements(By.xpath("//*[@id='myTab']/li"));
						
			for ( int i=2;i<=Tab.size();i++)
			
			{
				
				TabData=driver.findElement(By.xpath("//*[@id='myTab']/li["+i+"]")).getText();				
				
				TabAPI= Integer.parseInt(TabData.replaceAll("[^0-9]", ""));
				
				TabString = TabData.replaceAll("[0-9() ]", "");				
				
				System.out.println("There are "+ TabAPI + " "+TabString.trim()+ " in this page");
				
				count = count+TabAPI;
				
				TabAPI =0;
				
				
			}
			
			System.out.println("SUM OF ALL APIS IS  "+count);
			
			driver.quit();
			
		}
}