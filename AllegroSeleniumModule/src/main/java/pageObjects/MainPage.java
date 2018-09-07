package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(xpath="//span[@class='metrum-cart-status']")
    public static WebElement cartBtn;



}
