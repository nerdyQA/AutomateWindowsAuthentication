package splus.AutomationWindowsAD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ManhattanReport {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();

		/*
		 * String userid="dxlmifqa"; String password="dxlmifqa3#"; String URL =
		 * "https://" +userid +":"+password+"@"+"dsgns.mif.manh.com/";
		 */

		driver.get("https://dsgns.mif.manh.com/");
		try {
			WindowsAuthenticationlogin("dxlmifqa", "dxlmifqa3#");
		} catch (Exception e) {

			e.printStackTrace();
		}
		int i=driver.findElements(By.id("iframe")).size();
		System.out.println("Total number of frames"+i);
		// Switching to main frame
		driver.switchTo().frame(0);
		// Switching to next iframe
		driver.switchTo().frame(1);
		// Click on Solutions Menu
		driver.findElement(By.xpath("//tr/td[@id='elmt_Solutions_subMenu']")).click();
		// Click on Manhattan Int. SubMenu
		driver.findElement(By.xpath("//*[@id='a/web/ILS/ILSHelpers/main.jsp']")).click();

		
		
		WebElement submenu=driver.findElement(By.xpath("//img[@id='tr_panel4img']"));
		Thread.sleep(4000);

		  //First Get parent window,means current window handle
		  String parentWindow = driver.getWindowHandle();
		  System.out.println("Parent Window Title " + driver.getTitle());

		  //click on Submenu link  to open window
		  submenu.click();

		  //Now here is the New Tab handling code

		  //Get All Tabs or Window handles and iterate using for each loop
		 /* for (String handle: driver.getWindowHandles()) {
			  
		   System.out.println(handle);
		   driver.switchTo().window(handle);

		  }
*/
		  //Click on Quick Filter Button
		  driver.findElement(By.id("fltrMode1")).sendKeys(Keys.CONTROL +"\t");
		  driver.findElement(By.xpath("//*[@id='fltrMode1']")).click();
	}

		  //Go back to Parent window
		  //driver.switchTo().window(parentWindow);
		 

		
		
		
		

		

	

	// Code to pass the credentials in Windows AUthneticatio
	public static void WindowsAuthenticationlogin(String sUserName, String sPassword) throws Exception {

		try {
			// wait - increase this wait period if required
			Thread.sleep(5000);

			// create robot for keyboard operations
			Robot rb = new Robot();

			// Enter user name by CTRL-V
			StringSelection username = new StringSelection(sUserName);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// tab to password entry field
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			// Enter password by CTRL-V
			StringSelection pwd = new StringSelection(sPassword);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// press enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);

			// wait
			Thread.sleep(5000);
		} catch (Exception ex) {
			System.out.println("Error in Login Thread: " + ex.getMessage());
		}
	}

	// Code for opening the new tab
	/*public static void switchTabs(String URLToClick) throws Exception {

		
		 * Robot r = new Robot(); r.keyPress(KeyEvent.VK_CONTROL);
		 * r.keyPress(KeyEvent.VK_T); r.keyRelease(KeyEvent.VK_CONTROL);
		 * r.keyRelease(KeyEvent.VK_T);
		 

		// switch using actions class
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();

		// opening the URL saved.
		driver.get(URLToClick);
	}*/

}
