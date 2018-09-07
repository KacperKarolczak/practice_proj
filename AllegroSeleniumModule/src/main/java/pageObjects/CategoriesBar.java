package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoriesBar {

    @FindAll({@FindBy(xpath="//div[@class='b73bd_3S0cO']")})
    public static List<WebElement> categoriesButtonsList;
}
