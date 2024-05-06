package createAccount;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Scenario3 {
    @Test
    public void createAccountTest() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // navigate to the site
        driver.get("https://dev.insurance.tekschool-students.com/");
        // click on Create Primary Account
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

        Thread.sleep(2000);
        // Validate error message

        WebElement textElement = driver.findElement(By.xpath("//div[contains(text(),'Account with email kaeenat.kawsar@tekschool.us is exist')]"));

        // Verify if the text is present on the page
        if (textElement.isDisplayed()) {
            System.out.println("The text is present on the page.");
        } else {
            System.out.println("The text is not present on the page.");
        }

        driver.quit();
    }}


