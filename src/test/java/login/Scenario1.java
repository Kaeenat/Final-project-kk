package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scenario1 {
    @Test
    public void loginTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // navigate to the site
        driver.get("https://dev.insurance.tekschool-students.com/");
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[3]")));

        // Check if the "dashboard" text is displayed
        if (dashboardText.isDisplayed()) {
            System.out.println("The 'dashboard' text is displayed on the page.");
        } else {
            System.out.println("The 'dashboard' text is not displayed on the page.");
        }

        driver.quit();
    }
}

