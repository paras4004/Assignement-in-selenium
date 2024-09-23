import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class main {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("visual_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();

		Select sorting = new Select(driver.findElement(By.className("product_sort_container")));
		sorting.selectByVisibleText("Name (Z to A)");

		List<WebElement> productname = driver.findElements(By.className("inventory_item_name")); // Get all product
																									// names after
																									// sorting
		List<String> actualProduct = new ArrayList<>();
		for (WebElement product : productname) {
			actualProduct.add(product.getText());
		}

		List<String> expectedproduct = new ArrayList<>(actualProduct); // Verify the sorting order Z-A
		Collections.sort(expectedproduct, Collections.reverseOrder());

		if (actualProduct.equals(expectedproduct)) {
			System.out.println("Test Passed: Products are sorted Z to A");
		} else {
			System.out.println("Test Failed: Sorting order is incorrect");
		}

	}
}
