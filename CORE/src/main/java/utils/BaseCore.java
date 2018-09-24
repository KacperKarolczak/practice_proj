package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


@Listeners({org.uncommons.reportng.HTMLReporter.class})
public class BaseCore {

    protected WebDriver driver;
    private WebDriverWait wait;
    protected Action action;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "../CORE/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        action = new Action(wait, driver);
        driver.manage().window().maximize();
        pageInitialization();

    }

    private void pageInitialization(){
        Page page = this.getClass().getAnnotation(Page.class);
        for(Class pageName: page.value()){
            PageFactory.initElements(driver, pageName);
        }
    }

    @AfterMethod
    public void takeScreenShotOfFailure(ITestResult testResult) throws IOException {
        System.out.println(testResult.getStatus());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/html/images/";
        File screenshotName = new File(path + testResult.getTestClass() + ".png");
        FileUtils.copyFile(scrFile, screenshotName);
        Reporter.log("<br>  <img src='images/" + testResult.getTestClass() + ".png'" + "><br>");}





    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
