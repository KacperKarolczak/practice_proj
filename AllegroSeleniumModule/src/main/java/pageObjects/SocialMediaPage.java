package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SocialMediaPage {

    @FindBy(xpath = "//a[@href='https://www.facebook.com/allegro']")
    public static WebElement facebookConnectionBtn;

    @FindBy(xpath = "//a[@href='https://www.linkedin.com/company/grupa-allegro']")
    public static WebElement linkedinConnectionBtn;
}
