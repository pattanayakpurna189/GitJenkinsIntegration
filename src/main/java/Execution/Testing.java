package Execution;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Testing {

	@Test
	public void method() {

		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		
		
		System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://chromedriver.chromium.org/downloads");
		driver.manage().window().maximize();
		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while(it.hasNext()){

				url = it.next().getAttribute("href");
		
				//System.out.println(url);
		
				if(url == null || url.isEmpty()){
					System.out.println("URL is either not configured for anchor tag or it is empty");
					continue;
				}
			
				try {
					huc = (HttpURLConnection)(new URL(url).openConnection());
			
					huc.setRequestMethod("HEAD");
			
					huc.connect();
			
					respCode = huc.getResponseCode();
			
					if(respCode >= 400){
					  System.out.println(url+" is a broken link");
					}
					else{
					  System.out.println(url+" is a valid link");
					}
		
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		driver.quit();
		
		
	
	}
}
