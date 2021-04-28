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
		System.out.println(links.get(1).toString());
		driver.quit();
		
		
	
	}
}
