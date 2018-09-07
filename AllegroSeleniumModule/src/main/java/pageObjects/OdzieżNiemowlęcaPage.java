package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OdzieżNiemowlęcaPage {

    @FindAll({@FindBy(xpath="//span[@class='ecb7eff']")})
    public static List<WebElement> odzieżNiemowlęcaFullPlnPricesList;


    @FindAll({@FindBy(xpath="//div[@class='_05fa65b']")})
    public static List<WebElement> odzieżNiemowlęcaProductBoxesList;

    @FindAll({@FindBy(xpath="//div[@class='_8ce3910']/h2/a")})
    public static List<WebElement> odzieżNiemowlęcaProductNamesList;


}
