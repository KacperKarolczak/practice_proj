package REST002;

import ImgurClient.ImgurClient;
import ImgurManager.ImgurManager;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static responses.GetImageCountResponse.getCount;
import static responses.PostImageResponse.getStatus;

public class postImageTest extends ImgurClient {
    private HttpResponse httpResponse;
    private String response;
    ImgurManager manager = new ImgurManager();
    String status;
    String accountId;
    String username;
    String count;
    String type = "jpg",title = "city";
    private Path path = Paths.get("src/main/resources/city.jpg");


    @Test
    public void sendRequest() throws IOException, ParseException, java.text.ParseException {
        status = manager.getResponsesData("OK_Status");
        accountId = manager.getResponsesData("accountId");
        username = manager.getAccountData("username");
        String imageBase64;
        imageBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        httpResponse = getImageCount(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        manager.logResponse(response);
        count = getCount(response);
        httpResponse = postImage(imageBase64,type,title);
        httpResponse= getImageCount(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        manager.logResponse(response);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
        Assert.assertEquals(getStatus(response),status);
        Reporter.log("Status is OK. Expected: " + status+ " ,found: " +  getStatus(response));
        Assert.assertEquals(Integer.parseInt(getCount(response)), Integer.parseInt(count) + 1);
        Reporter.log("Image count is OK. Expected: " + (Integer.parseInt(count) + 1) + " ,found: " +  getCount(response));
    }
}
