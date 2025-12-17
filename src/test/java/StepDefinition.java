import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinition {

    private WebDriver driver;

    @Before  // Körs innan varje scenario
    public void setUp() {
        // Tvinga rensa cachen varje gång och logga mer detaljerat
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();  // Laddar ner och sätter upp chromedriver automatiskt

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

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
        // Kan vara tom nu, eller lägg en extra navigering om behövs
    }

    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    @After  // Körs efter varje scenario
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

}
