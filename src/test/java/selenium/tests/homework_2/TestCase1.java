package selenium.tests.homework_2;

import org.testng.annotations.Test;
import selenium.tests.TestBase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCase1 extends TestBase {

    @Test (testName = "Check the logo is present")
    public void checkLogoPresence() {
        assertTrue(app.homepageHelper.isLogoDisplayed(),
                "The logo is not displayed");
    }

    @Test (testName = "Check 8 popular products are present",
    dependsOnMethods = "checkLogoPresence",
    alwaysRun = true)
    public void checkPopularProductsPresence() {
        assertTrue(app.homepageHelper.isPopularProductsPresent(),
                "The popular products are not displayed");
        assertEquals(app.homepageHelper.getAmountOfPopularProducts(), 8,
                "The amount of popular products is not 8");
    }
}
