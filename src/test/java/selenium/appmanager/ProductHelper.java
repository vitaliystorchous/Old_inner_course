package selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ProductHelper extends HelperBase {
    public ProductHelper(WebDriver wd) {
        super(wd);
    }

    public void setAmount(int amountOfProducts) {
        switchToProductOrderingModal();
        type(By.cssSelector("#quantity_wanted"), Integer.toString(amountOfProducts));
        wd.switchTo().defaultContent();
    }

    private void switchToProductOrderingModal() {
        WebElement productOrderingFrame = wd.findElement(By.cssSelector(".fancybox-iframe"));
        wd.switchTo().frame(productOrderingFrame);
    }

    public void addToCart() {
        switchToProductOrderingModal();
        wait.until(elementToBeClickable(By.cssSelector("#add_to_cart button"))).click();
        wd.switchTo().defaultContent();
    }

    public void closeModal() {
        switchToProductOrderingModal();
        wait.until(elementToBeClickable(By.cssSelector(".cross"))).click();
        wd.switchTo().defaultContent();
    }
}
