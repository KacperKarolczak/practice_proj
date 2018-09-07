package USSocialMedia;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FacebookPage;
import pageObjects.SocialMediaPage;
import utils.BaseTest;
import utils.Page;

@Page({SocialMediaPage.class,FacebookPage.class})
public class facebookConnectionTest extends BaseTest {

    @Test
    public void runTest(){

        action.click(SocialMediaPage.facebookConnectionBtn,"fbbtn");
        action.changeTab("facebookTab");
        Assert.assertEquals(FacebookPage.facebookIcon.getText(),"Facebook");
    }
}
