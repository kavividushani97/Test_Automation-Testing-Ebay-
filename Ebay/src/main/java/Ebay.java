import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ebay {
	
	String url = "https://www.ebay.com/";
	WebDriver driver;
	
	public void callBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(url);
		}
	
	public void searchProduct(String proName, String proCat) {
		driver.findElement(By.id("gh-ac")).sendKeys(proName);
		driver.findElement(By.id("gh-cat")).sendKeys(proCat);
		
		driver.findElement(By.id("gh-btn")).click();
		}
	
	public void print() {
		WebElement results = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1"));
		String re = results.getAttribute("textContent");
		System.out.println(re);
		}
	
	public void getNthProduct(int prodId) {
		
		///----------------------------Way 01 UsingXpath-------------------------------------------------
		
		/*String URL = "//*[@id=\"srp-river-results\"]/ul/li";
		String result =URL.concat("[".concat(Integer.toString(prodId)).concat("]"));
		WebElement Product = driver.findElement(By.xpath(result));
		System.out.println();
		System.out.println("***********************");
		System.out.println(Product.getText());
		System.out.println("***********************");
		System.out.println();*/
		
		///----------------------------Way 02 UsingXpath-------------------------------------------------
		
		WebElement Product = driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li"+"["+prodId+"]"));
		
		
		System.out.println("*********************************************************************");
		System.out.println(Product.getText());
		System.out.println("*********************************************************************");
		
		///----------------------------Way 03-------------------------------------------------
		
		/*List<WebElement> allProducts = driver.findElements(By.xpath("//*[@id=\"srp-river-results\"]/ul/li"));
		int count = 1;
		for( WebElement product : allProducts){
			
			if(count == prodId) {
				System.out.println("*********************************************************************");
				
				System.out.println(product.getText());

				System.out.println("*********************************************************************");
			}
			count++;
			
			}*/

	}
	
	public void printAllResult() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//*[@id=\"srp-river-results\"]/ul/li"));
		
		for( WebElement product : allProducts){
			
			
				System.out.println("*********************************************************************");
				
				System.out.println(product.getText());

				System.out.println("*********************************************************************");
			
			
			}
	}
	
	public void printAllResultWithScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		List<WebElement> allProducts = driver.findElements(By.xpath("//*[@id=\"srp-river-results\"]/ul/li"));
		int count = 1;
		
		for( WebElement product : allProducts){
			
			
			
			js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li["+count+"]")));
			
			System.out.println("*********************************************************************");
			
			System.out.println(product.getText());

			System.out.println("*********************************************************************");
			
			count++;
		}
   
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ebay eb = new Ebay();
		eb.callBrowser();
		eb.searchProduct("Apple watches", "Jewelry & Watches");
		eb.print();
		eb.getNthProduct(5);
		eb.printAllResult();
		eb.printAllResultWithScroll();

	}

}
