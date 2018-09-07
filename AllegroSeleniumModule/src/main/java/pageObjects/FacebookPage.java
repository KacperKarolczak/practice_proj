package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookPage {

    @FindBy(xpath = "//a[@href='https://www.facebook.com/']/i/u")
    public static WebElement facebookIcon;
}
