package appmanager;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ProductHelper extends HelperBase {
    public ProductHelper(WebDriver wd) {
        super(wd);
    }

    public void setAmount(int amountOfProducts) {
        type(By.cssSelector("#quantity_wanted"), Integer.toString(amountOfProducts));
    }

    public void addToCart() {
        wait.until(elementToBeClickable(By.cssSelector("#add_to_cart button"))).click();
    }

    public void closeModal() {
        wait.until(elementToBeClickable(By.cssSelector(".cross"))).click();
    }
}
