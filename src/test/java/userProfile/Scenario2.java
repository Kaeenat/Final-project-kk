package userProfile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scenario2 {
    @Test
    public void testProfileInformation() {
        ChromeDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.insurance.tekschool-students.com/");

        // Click on the login button
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/a[2]"));
        loginButton.click();

        // Enter username and password
        String username = "supervisor";
        String password = "tek_supervisor";
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        // Submit login form
        WebElement loginForm = driver.findElement(By.tagName("form"));
        loginForm.submit();

        // Wait for profile button to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[1]/div/button")));

        // Click on profile button
        profileButton.click();

        // Click on logout button
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"chakra-modal-:r3:\"]/footer/div/button[1]"));
        logoutButton.click();

        // Verify if user navigates to the home page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://dev.insurance.tekschool-students.com/", "User failed to navigate to the home page after logout.");
    }

}
