package createAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class scenario1 {
        ChromeDriver driver;

        @BeforeClass
        public void setup() {
            // Initialize ChromeDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Test
        public void validateFormTitle() {
            // Navigate to the website
            driver.get("https://dev.insurance.tekschool-students.com/");

            // Click on Create Primary Account
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/a")).click();

            // Find the form title element using XPath
            WebElement formTitle = driver.findElement(By.xpath("//h2[contains(text(),'Create Primary Account Holder')]"));

            // Get the text of the form title
            String actualTitle = formTitle.getText();

            // Define the expected title
            String expectedTitle = "Create Primary Account Holder";

            // Validate the form title
            Assert.assertEquals(actualTitle, expectedTitle, "Form title validation failed.");
        }

        @AfterClass
        public void tearDown() {
            // Close the browser
            driver.quit();
        }
    }

