package USFiltring;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.BaseTest;
import utils.Page;

import java.util.ArrayList;

@Page({CategoriesBar.class,DzieckoCategoryPage.class, FiltringOptionsPage.class, OdzieżNiemowlęcaPage.class})
public class betweenValuesFiltringTest extends BaseTest {

    String minValue = "100";
    String maxValue = "101";
    @Test
    public void runTest(){
        action.click(CategoriesBar.categoriesButtonsList.get(4),"dziecko btn");
        action.click(DzieckoCategoryPage.odzieżNiemowlęcaBtn,"odziez niemowleca btn");
        action.type(FiltringOptionsPage.priceFromInput,minValue,"price from input");
        action.type(FiltringOptionsPage.priceToInput,maxValue,"price to input");

        ArrayList<String> arraylist =new ArrayList<String>();
        for (int i = 0; i < OdzieżNiemowlęcaPage.odzieżNiemowlęcaFullPlnPricesList.size(); i++){
            arraylist.add(OdzieżNiemowlęcaPage.odzieżNiemowlęcaFullPlnPricesList.get(i).getText());
        }

        Assert.assertTrue(action.isBetween(arraylist,minValue,maxValue));

    }
}
