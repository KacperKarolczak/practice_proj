package REST004;

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
import static responses.PostImageResponse.getImageId;

public class deleteFromGalleryTest extends ImgurClient {
    private HttpResponse httpResponse;
    private String response;
    ImgurManager manager = new ImgurManager();
    String username;
    String count;
    String count2;
    String count3;
    String newImageID;
    String type = "jpg",title = "city";
    private Path path = Paths.get("src/main/resources/city.jpg");


    @Test
    public void sendRequest() throws IOException, ParseException, java.text.ParseException {
        String imageBase64;
        imageBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        username = manager.getAccountData("username");

        httpResponse = getImageCount(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        count = getCount(response);
        Reporter.log("Number of pictures before adding a new photo: " + count);

        httpResponse = postImage(imageBase64,type,title);
        response = EntityUtils.toString(httpResponse.getEntity());
        newImageID = getImageId(response);
        Reporter.log("New Image ID: " + newImageID);

        httpResponse = getImageCount(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        count2 = getCount(response);
        Reporter.log("Number of pictures after adding a new photo: " + count2);

        httpResponse = deleteImageFromGallery(newImageID);
        response = EntityUtils.toString(httpResponse.getEntity());
        Reporter.log("Response after removing an image from the gallery: ");
        manager.logResponse(response);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);

        httpResponse = getImageCount(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        count3 = getCount(response);
        Reporter.log("Number of pictures in the gallery after removing a photo: " + count3);
        Assert.assertEquals(count, count3);
        Reporter.log("Action successfully completed. Number of photos before action: " + count + ". Numbers of photos after action: " + count3);
    }
}