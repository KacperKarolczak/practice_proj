package utils;

import managers.ConfigManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest extends BaseCore {
    public String usernameEmail;
    public String usernamePassword;
    ConfigManager manager = new ConfigManager();

    @BeforeClass
    public void setUpTest() throws IOException, ParseException {
        usernameEmail = manager.getProperty("usernameEmail");
        usernamePassword = manager.getProperty("usernamePassword");
        driver.navigate().to("https://www.allegro.pl");
        driver.findElement(By.xpath("(//button[@data-role='accept-consent'])[2]")).click();

    }
}