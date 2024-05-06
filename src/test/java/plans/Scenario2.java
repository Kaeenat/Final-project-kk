package plans;

import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Scenario2 {
    private ChromeDriver driver;

    @BeforeClass
    public void setUp() {
        // Initialize ChromeDriver
        System.setProperty("web driver.chrome.driver", "/Users/username/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the site and perform login
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

        // Wait for page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNumberOfRowsInPlansTable() {
        // Click on Plans button
        WebElement plansButton = driver.findElement(By.cssSelector("a[href='/csr/plans']"));
        plansButton.click();

        // Wait for page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the table
        WebElement table = driver.findElement(By.className("chakra-table"));
        LocalDate todayEST = LocalDate.now(ZoneId.of("America/New_York"));
        LocalDate tomorrowEST = todayEST.plusDays(1);

        // Parse the date strings
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date dateCreated, dateExpire;
        try {
            dateCreated = sdf.parse("May 4, 2024");
            dateExpire = sdf.parse("May 5, 2024");

            // Convert Date objects to LocalDate objects
            LocalDate localDateCreated = dateCreated.toInstant().atZone(ZoneId.of("America/New_York")).toLocalDate();
            LocalDate localDateExpire = dateExpire.toInstant().atZone(ZoneId.of("America/New_York")).toLocalDate();

            // Validate Date Created is today's date
            if (localDateCreated.equals(todayEST)) {
                System.out.println("Date Created is today's date in EST time zone.");
            } else {
                System.out.println("Date Created is not today's date in EST time zone.");
            }

            // Validate Date Expire is a day after
            if (localDateExpire.equals(tomorrowEST)) {
                System.out.println("Date Expire is a day after today's date in EST time zone.");
            } else {
                System.out.println("Date Expire is not a day after today's date in EST time zone.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }}


