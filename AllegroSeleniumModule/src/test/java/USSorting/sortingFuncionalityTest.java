package USSorting;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.CategoriesBar;
import pageObjects.ChoosedCategoryPage;
import pageObjects.DzieckoCategoryPage;
import pageObjects.OdzieżNiemowlęcaPage;
import utils.BaseTest;
import utils.Page;

import java.util.ArrayList;


@Page({CategoriesBar.class,DzieckoCategoryPage.class, ChoosedCategoryPage.class, OdzieżNiemowlęcaPage.class})
public class sortingFuncionalityTest extends BaseTest {


    @Test
    public void runTest(){

        action.click(CategoriesBar.categoriesButtonsList.get(4),"dziecko category");
        action.click(DzieckoCategoryPage.odzieżNiemowlęcaBtn,"od niem btn");
        action.click(ChoosedCategoryPage.sortingSelect,"sort selec");
        action.click(ChoosedCategoryPage.sortingPriceDsc,"sort Asc");

        ArrayList<String> arraylist = new ArrayList<String>();
        for (int i = 3; i < OdzieżNiemowlęcaPage.odzieżNiemowlęcaFullPlnPricesList.size() - 2 ; i++){
            arraylist.add(OdzieżNiemowlęcaPage.odzieżNiemowlęcaFullPlnPricesList.get(i).getText());
        }

        action.isSortedListDesc(arraylist);
        System.out.println(action.isSortedListDesc(arraylist));
        Assert.assertTrue(action.isSortedListDesc(arraylist));



    }
}
