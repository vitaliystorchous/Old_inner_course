package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Integer.parseInt;

public class CartButtonHelper extends HelperBase {
    public CartButtonHelper(WebDriver wd) {
        super(wd);
    }

    public int getAmountOfAddedProducts() {
        return parseInt(wd.findElement(By.cssSelector(".shopping_cart .ajax_cart_quantity"))
                .getAttribute("innerText"));
    }
}
