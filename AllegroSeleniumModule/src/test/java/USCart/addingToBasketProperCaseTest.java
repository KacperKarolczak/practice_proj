package USCart;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.BaseTest;
import utils.Page;

@Page({MainPage.class,CategoriesBar.class,DzieckoCategoryPage.class, OdzieżNiemowlęcaPage.class,ProductPage.class,CartPage.class})
public class addingToBasketProperCaseTest extends BaseTest {

    @Test
    public void runTest() {

        action.click(CategoriesBar.categoriesButtonsList.get(4),"dziecko category");
        action.click(DzieckoCategoryPage.odzieżNiemowlęcaBtn,"od niem btn");

        String FullPlnOnProductBox = OdzieżNiemowlęcaPage.odzieżNiemowlęcaFullPlnPricesList.get(3).getText();
        String ProductName = OdzieżNiemowlęcaPage.odzieżNiemowlęcaProductNamesList.get(3).getText();
        System.out.println(FullPlnOnProductBox);
        System.out.println(ProductName);

        action.click(OdzieżNiemowlęcaPage.odzieżNiemowlęcaProductBoxesList .get(3),"fourth product");
        action.click(ProductPage.addToCartBtn,"add to cart");
        action.click(MainPage.cartBtn,"cart button");

        String FullPlnInCart = CartPage.productsInCartFullPlnPricesList.get(0).getText();
        String ProductNameInCart = CartPage.productsInCartNamesList.get(0).getText();
        System.out.println(ProductNameInCart);
        System.out.println(FullPlnInCart);
        Assert.assertEquals(FullPlnOnProductBox,FullPlnInCart);
        Assert.assertEquals(ProductName,ProductNameInCart);


    }}
