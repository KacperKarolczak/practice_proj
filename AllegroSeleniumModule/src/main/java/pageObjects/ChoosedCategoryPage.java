package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChoosedCategoryPage {

    @FindBy(xpath ="//select[@class='_1akfs _o2u5x _1pffh']")
    public static WebElement sortingSelect;

    @FindBy(xpath = "//option[@value='p']")
    public static WebElement sortingPriceAsc;

    @FindBy(xpath = "//option[@value='pd']")
    public static WebElement sortingPriceDsc;
}
