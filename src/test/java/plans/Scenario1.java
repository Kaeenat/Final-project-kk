package plans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Scenario1 {


    @Test
    public void testNumberOfRowsInPlansTable() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the site
        driver.get("https://dev.insurance.tekschool-students.com/");

        // Login
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/a[2]"));
        loginButton.click();
        String username = "supervisor";
        String password = "tek_supervisor";

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement loginForm = driver.findElement(By.tagName("form"));
        loginForm.submit();

        Thread.sleep(3000);

        // Click on Plans button
        WebElement plansButton = driver.findElement(By.cssSelector("a[href='/csr/plans']"));
        plansButton.click();

        Thread.sleep(3000);

        // Get the table
        WebElement table = driver.findElement(By.className("chakra-table"));

        // Get all rows in the table body
        List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        // Verify the number of rows
        Assert.assertEquals(rows.size(), 4, "Unexpected number of rows in the Plans table.");


    }

}

