import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Checkout {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/");

		// Login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
		// Add multiple items to the cart
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		Thread.sleep(3000);

		// Go to cart and checkout
		driver.findElement(By.className("shopping_cart_link")).click();
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(3000);

		// Fill in checkout details
		driver.findElement(By.id("first-name")).sendKeys("paras");
		driver.findElement(By.id("last-name")).sendKeys("sharma");
		driver.findElement(By.id("postal-code")).sendKeys("160062");
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);

		// Validate checkout overview page
		String totalAmount = driver.findElement(By.className("summary_total_label")).getText();
		System.out.println("Total Amount: " + totalAmount);

		driver.quit();
	}
}
