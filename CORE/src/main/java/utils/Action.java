package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Action {
    private WebDriverWait wait;
    private WebDriver driver;

    public Action(WebDriverWait wait, WebDriver driver){
        this.wait = wait;
        this.driver = driver;
    }


    public void click(WebElement element, String elementName){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (NullPointerException e) {
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch(Exception e){
            Assert.assertTrue(false,"Element '" + elementName + "' is not clickable");
            return;
        }
        Reporter.log("Element '" + elementName + "' was successfully clicked<br>");
    }


    public void type(WebElement element, String text, String elementName){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
        }catch (NullPointerException e) {
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch(Exception e){
            Assert.assertTrue(false,"Couldn't type text '" + text + "' in element '" + elementName + "'");
            return;
        }
        Reporter.log("Text " + text + " was successfully type into element '" + elementName + "'<br>");
    }

    public void waitForElement(WebElement element, String   elementName){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid #696969;', 'border radius: 6px');", element);
        }catch (NullPointerException e) {
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch (Exception e){
            Assert.assertTrue(false,"element " + elementName + " is not visible");
            return;
        }
        Reporter.log("Element '" + elementName + "' was successfully displayed<br>");
    }
    public void changeTab(String elementName){
        try {
            List<String> openedTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(openedTabs.get(1));
        }catch (NullPointerException e){
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch (Exception e){
            Assert.assertTrue(false, "Tab " + elementName + " is not open");
            return;
        }
        Reporter.log("Tab was successfully changed<br>");
    }

    public void selectFromDropDownList(WebElement dropDownElement , int listPositionIndex, String name){
        try{
            Select select = new Select(dropDownElement);
            select.selectByIndex(listPositionIndex);
        }catch (NullPointerException e){
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch (Exception e){
            Assert.assertTrue(false, "ELEMENT " + name + " IS NOT AVAILABLE");
            return;
        }
        Reporter.log("Drop down list position successfully selected<br>");
    }

    public void attachFile(WebElement element, File file, String name){
        try {
            wait.until(ExpectedConditions.attributeContains(element, "type", "file"));
            element.sendKeys(file.getAbsolutePath());
        }catch (NullPointerException e){
            Assert.assertFalse(false,"NULL POINTER EXCEPTION - CHECK TEST @Page ANNOTATION");
        }catch (Exception e){
            Assert.assertTrue(false, "CAN NOT ATTACH FILE TO " + name);
            return;
        }
        Reporter.log("File successfully uploaded<br>");
    }

    public void checkAllTexts(List<WebElement> elementsList, String[] text, String name ){
        try {
            wait.until(ExpectedConditions.visibilityOf(elementsList.get(0)));
            for (int i = 0; i < elementsList.size(); i++){
                Assert.assertEquals(elementsList.get(i).getText(), text[i],
                        name + " VALUE IS DIFFERENT THEN EXPECTED");
            }
        }catch (IndexOutOfBoundsException e){
            Assert.assertTrue(false,"NUMBER OF " + name + " IS DIFFERENT THEN EXPECTED");
        }catch (Exception e){
            Assert.assertTrue(false, "CAN NOT CHECK " + name + " ELEMENTS");
        }
        Reporter.log("All " + name + " texts successfully checked<br>");
    }
    public List<String> createListOfString(List<WebElement> elementsList, String s){
        try{
            wait.until(ExpectedConditions.visibilityOf(elementsList.get(0)));
            List<String> arrayList= new ArrayList<String>();
            for (WebElement we : elementsList){
                arrayList.add(we.getText());
            }
            Collections.sort(arrayList);
            System.out.println(arrayList);
        }catch (Exception e){
            Assert.assertTrue(false, "CAN NOT SORT List ");
            return null;
        }
        return null;
    }
    public ArrayList<Integer> createArrayListOfIntegers(List<WebElement> elementsList, String s){
        try{
            wait.until(ExpectedConditions.visibilityOf(elementsList.get(0)));
            ArrayList<Integer> arrayList= new ArrayList<Integer>();
            for (int i = 0;i<elementsList.size();i++){
                arrayList.add(Integer.parseInt(elementsList.get(i).getText()));
            }
            Collections.sort(arrayList);
            System.out.println(arrayList);
        }catch (Exception e){
            Assert.assertTrue(false, "CAN NOT SORT List ");
            return null;
        }
        return null;
    }
    public static boolean isSortedListAsc(List<? extends Comparable> list)
    {
        if(list == null || list.isEmpty())
            return false;

        if(list.size() == 1)
            return true;

        for(int i=1; i<list.size();i++)
        {
            if(list.get(i).compareTo(list.get(i-1)) > 0 )
                return true;
        }

        return false;
    }
    public static boolean isSortedListDesc(List<? extends Comparable> list)
    {
        if(list == null || list.isEmpty())
            return false;

        if(list.size() == 1)
            return true;

        for(int i=1; i<list.size();i++)
        {
            if(list.get(i).compareTo(list.get(i-1)) < 0 )
                return true;
        }

        return false;
    }
    public static boolean isBetween(List<? extends Comparable>list, String min, String  max){

        if(list == null || list.isEmpty())
            return false;

        for (int i =0; i<list.size();i++){
            if (list.get(i).compareTo(min) >= 0 && list.get(i).compareTo(max) <= 0)
                return  true;
        }
        return false;
    }

    public static void sleepCode(int miliSecond){
        try {
            Thread.sleep(miliSecond);
        }catch(Exception e){
            Assert.assertTrue(false," wrong argument :" +miliSecond);
        }
        Reporter.log( "Successfully waited.<br>");
    }
}
