package Accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scenario1 {
    private ChromeDriver driver;

    @BeforeClass
    public void setUp() {
        // Initialize ChromeDriver and navigate to the site
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.insurance.tekschool-students.com/");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after all tests are executed
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginAndNavigateToAccounts() {
        // Perform login
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

        // Wait until the Accounts link is clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement accountsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/a[1]")));
        accountsLink.click();
    }

    @Test(dependsOnMethods = "testLoginAndNavigateToAccounts")
    public void testNumberOfRowsInAccountsTable() {
        // Wait until the table of accounts is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement accountsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("chakra-table")));

        // Get all rows of the table
        int numberOfRows = accountsTable.findElements(By.tagName("tr")).size();

        // Print the number of rows in the table
        System.out.println("Number of rows in the table: " + (numberOfRows - 1)); // excluding header row
    }

}
