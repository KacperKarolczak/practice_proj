package REST003;

import ImgurClient.ImgurClient;
import ImgurManager.ImgurManager;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;

import static responses.GetAccountImageResponse.getImageInformationsIdlist;
import static responses.GetImageIDsResponse.getIdlist;

public class getImageIDsTest extends ImgurClient {
    private HttpResponse httpResponse;
    private String response1;
    private String response2;
    ImgurManager manager = new ImgurManager();
    String username;

    @Test
    public void sendRequest() throws ParseException, java.text.ParseException, IOException {
        username = manager.getAccountData("username");
        httpResponse = getAccountImages(username);
        response1 = EntityUtils.toString(httpResponse.getEntity());
        manager.logResponse(response1);
        httpResponse = getImageIDs(username);
        response2 = EntityUtils.toString(httpResponse.getEntity());
        manager.logResponse(response2);
        for (int i = 0 ; i<getImageInformationsIdlist(response1).size(); i++) {
            Assert.assertEquals(getImageInformationsIdlist(response1).get(i),getIdlist(response2).get(i));
            Reporter.log("List of ids of images is ok.Expected: " + getImageInformationsIdlist(response1).get(i)+ " Found: " + getIdlist(response2).get(i));
        }

    }
}

