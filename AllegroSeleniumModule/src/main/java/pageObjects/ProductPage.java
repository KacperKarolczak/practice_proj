package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    @FindBy(id="add-to-cart")
    public static WebElement addToCartBtn;

}
