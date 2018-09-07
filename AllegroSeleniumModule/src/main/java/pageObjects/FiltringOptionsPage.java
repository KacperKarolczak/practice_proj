package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltringOptionsPage {

    @FindBy(id = "price_from")
    public static WebElement priceFromInput;

    @FindBy(id = "price_to")
    public static WebElement priceToInput;
}
