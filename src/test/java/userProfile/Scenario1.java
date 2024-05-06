package userProfile;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scenario1 {
    @Test
    public void testProfileInformation() {
        ChromeDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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

        // Wait for profile button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Increased timeout
        try {
            WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='profile']")));

            // Click on the profile button
            profileButton.click();
        } catch (TimeoutException e) {
            // Handle timeout
            System.out.println("Timed out waiting for profile button to be clickable.");
            WebElement statusElement = driver.findElement(By.xpath("//p[contains(text(), 'STATUS')]/following-sibling::p/span"));
            WebElement userTypeElement = driver.findElement(By.xpath("//p[contains(text(), 'USER TYPE')]/following-sibling::p"));
            WebElement fullNameElement = driver.findElement(By.xpath("//p[contains(text(), 'FULL NAME')]/following-sibling::p"));
            WebElement usernameProfileElement = driver.findElement(By.xpath("//p[contains(text(), 'USERNAME')]/following-sibling::p"));
            WebElement authoritiesElement = driver.findElement(By.xpath("//p[contains(text(), 'AUTHORITIES')]/following-sibling::ul/li"));

            String actualStatus = statusElement.getText();
            String actualUserType = userTypeElement.getText();
            String actualFullName = fullNameElement.getText();
            String actualUsername = usernameProfileElement.getText();
            String actualAuthorities = authoritiesElement.getText();

            // Expected values
            String expectedStatus = "Active";
            String expectedUserType = "CSR";
            String expectedFullName = "Supervisor";
            String expectedUsername = "supervisor";
            String expectedAuthorities = "admin";

            // Assertion
            Assert.assertEquals(actualStatus, expectedStatus, "Status in profile side drawer is incorrect.");
            Assert.assertEquals(actualUserType, expectedUserType, "User type in profile side drawer is incorrect.");
            Assert.assertEquals(actualFullName, expectedFullName, "Full name in profile side drawer is incorrect.");
            Assert.assertEquals(actualUsername, expectedUsername, "Username in profile side drawer is incorrect.");
            Assert.assertEquals(actualAuthorities, expectedAuthorities, "Authorities in profile side drawer are incorrect.");
        }
    }
}
