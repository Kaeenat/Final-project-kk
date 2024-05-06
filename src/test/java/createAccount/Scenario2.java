package createAccount;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Scenario2 {
    @Test
    public void testCreateAccount() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Navigate to the site
        driver.get("https://dev.insurance.tekschool-students.com/");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/a")).click();
        WebElement firstNameInput = driver.findElement(By.name("firstName"));
        firstNameInput.sendKeys("Kaeenat");

        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameInput.sendKeys("Kawsar");

        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailInput.sendKeys("kaeenat.kawsar@tekschool.us");

        WebElement optionMrs = driver.findElement(By.xpath("//*[@id='title']//option[contains(text(),'Mrs.')]"));
        optionMrs.click();

        WebElement genderDropdown = driver.findElement(By.xpath("//*[@id='gender']"));
        genderDropdown.click();

        WebElement femaleOption = driver.findElement(By.xpath("//*[@id='gender']//option[contains(text(),'Female')]"));
        femaleOption.click();

        WebElement maritalStatusDropdown = driver.findElement(By.xpath("//*[@id='maritalStatus']"));
        maritalStatusDropdown.click();

        WebElement marriedOption = driver.findElement(By.xpath("//*[@id='maritalStatus']//option[contains(text(),'Married')]"));
        marriedOption.click();

        WebElement dobInput = driver.findElement(By.xpath("//*[@id='dateOfBirth']"));
        dobInput.sendKeys("12/20/2003");

        WebElement createAccountButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div/form/div/div[2]/button[1]"));
        createAccountButton.click();

        // Validate the titles
        validateTitle(driver, "Sign up your account");
        validateTitle(driver, "Kaeenat Kawsar");
        validateTitle(driver, "kaeenat.kawsar@tekschool.us");

        // Close the browser
        driver.quit();
    }

    private static void validateTitle(ChromeDriver driver, String expectedTitle) {
        WebElement titleElement = driver.findElement(By.xpath("//h2[contains(text(),'" + expectedTitle + "')]"));
        String actualTitle = titleElement.getText();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title validation passed: " + expectedTitle);
        } else {
            System.out.println("Title validation failed. Expected: " + expectedTitle + ", Actual: " + actualTitle);
        }
    }
}


