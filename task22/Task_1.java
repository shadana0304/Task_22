package com.task22;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task_1 {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://phptravels.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		String expected="Thank You!";
		//form detailes filled
		driver.findElement(By.name("first_name")).sendKeys("text");
        driver.findElement(By.name("last_name")).sendKeys("user");
        driver.findElement(By.name("business_name")).sendKeys("tester");
        driver.findElement(By.name("email")).sendKeys("stop123@gamil.com");
        
        //sum verification
        WebElement num1=driver.findElement(By.id("numb1"));
        WebElement num2=driver.findElement(By.id("numb2"));
        
       String a= num1.getText();
       String b= num2.getText();
       
       int sum1=0;
       for(int i=0;i<a.length();i++) {
    	   if(Character.isDigit(a.charAt(i)))
    		   sum1=sum1+Character.getNumericValue(a.charAt(i));
       }
       int sum2=0;
       for(int i=0;i<b.length();i++) {
    	   if(Character.isDigit(b.charAt(i)))
    		   sum2=sum2+Character.getNumericValue(b.charAt(i));
    	   
       }
       int sum = sum1+sum2;
       String s=String.valueOf(sum);
       driver.findElement(By.id("number")).sendKeys(s);
     
       // submit the form
       WebElement submit = driver.findElement(By.id("demo"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", submit);  
		
		//check the verification message
       WebElement msg = driver.findElement(By.xpath("//div[@class='completed']/child::h2"));
		String actual = msg.getText();
		if(actual.equalsIgnoreCase(expected)) {
			System.out.println("Verified");
		}
        
		//after form submisssion take the screenshot
        TakesScreenshot screenshot=((TakesScreenshot)driver);
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File dest=new File("ScreenShot/phptravels.png");
        FileUtils.copyFile(source, dest);
        
        
        
        
        
	}

}
