import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinition {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Given("The Shop is available")
    public void the_shop_is_available() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");

        // Vänta max 20 sekunder tills titeln är "The Shop"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.titleIs("The Shop"));
    }

    @When("User visits The Shop")
    public void user_visits_the_shop() {
        // Eventuell extra navigering kan läggas här
    }

    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    @After
    public void closeBrowser() {
        try {
            Thread.sleep(3000); // 3 sekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
