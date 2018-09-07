package REST001;

import ImgurClient.ImgurClient;
import ImgurManager.ImgurManager;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static responses.GetAccountBaseResponse.*;

public class getAccountBaseTest extends ImgurClient {

    private HttpResponse httpResponse;
    private String response;
    ImgurManager manager = new ImgurManager();
    String username;
    String accountUrl;
    String accountId;
    String accountAvatar;



    @Test
    public void sendRequest() throws IOException, ParseException, java.text.ParseException {

        accountUrl = manager.getResponsesData("accountUrl");
        accountId = manager.getResponsesData("accountId");
        accountAvatar = manager.getResponsesData("accountAvatarName");
        username = manager.getAccountData("username");
        httpResponse = getAccountBase(username);
        response = EntityUtils.toString(httpResponse.getEntity());
        manager.logResponse(response);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
        Assert.assertEquals(getUrl(response), accountUrl);
        Reporter.log("AccountURL is OK. Expected: " + accountUrl+ " ,found: " +  getUrl(response));
        Assert.assertEquals(getId(response), accountId);
        Reporter.log("AccountID is OK. Expected: " + accountId+ " ,found: " +  getId(response));
        Assert.assertEquals(getAvatar_name(response), accountAvatar);
        Reporter.log("AccountAvatarName is OK. Expected: " + accountAvatar+ " ,found: " +  getAvatar_name(response));


    }

}

