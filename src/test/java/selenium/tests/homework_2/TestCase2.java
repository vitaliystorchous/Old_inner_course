package selenium.tests.homework_2;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import selenium.tests.TestBase;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.testng.Assert.assertEquals;

public class TestCase2 extends TestBase {

    @Test (testName = "The cart's counter shows right amount of added products")
    public void testCart() {
        app.homepage().openRandomPopularProduct();
        int amountOfProducts = nextInt(1, 101);
        app.product().setAmount(amountOfProducts);
        app.product().addToCart();
        try {
            app.product().closeModal();
        } catch (TimeoutException ex) {
            System.out.println("The modal was not displayed");
            /*throw ex;*/
        }
        app.refresh();
        assertEquals(app.cartButton().getAmountOfAddedProducts(), amountOfProducts,
                "The amount of products in cart is not equal to added amount of products");
    }
}
