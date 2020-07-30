package selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomepageHelper extends HelperBase {
    public HomepageHelper(WebDriver wd) {
        super(wd);
    }

    public boolean isLogoDisplayed() {
        return isElementVisible(By.cssSelector(".logo"));
    }

    public boolean isPopularProductsPresent() {
        click(By.cssSelector("a.homefeatured"));
        return isElementPresent(By.cssSelector("ul.homefeatured .product-container"));
    }

    public int getAmountOfPopularProducts() {
        return wd.findElements(By.cssSelector("ul.homefeatured .product-container")).size();
    }

    public void openRandomPopularProduct() {
        Actions action = new Actions(wd);
        WebElement product = wd.findElement(By.cssSelector("ul#homefeatured li:nth-child(" + nextInt(1, 9) + ")"));
        action.moveToElement(product).perform();
        wait.until(elementToBeClickable(product.findElement(By.cssSelector(".quick-view")))).click();
    }
}
