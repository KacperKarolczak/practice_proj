package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage {
    @FindAll({@FindBy(xpath="//span[@class='m-price ng-binding m-price--primary']")})
    public static List<WebElement> productsInCartFullPlnPricesList;

    @FindAll({@FindBy(xpath="//div[@class='offer-title--cell']/div/a")})
    public static List<WebElement> productsInCartNamesList;


}
