package ImgurClient;

import ImgurManager.ImgurManager;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static responses.RefreshToken.get_accessToken;
import static responses.RefreshToken.get_refreshToken;

@Listeners({org.uncommons.reportng.HTMLReporter.class})

public class ImgurClient {
    private static final Logger logger = Logger.getLogger(ImgurClient.class);
    private HttpClient client;
    private HttpGet getRequest;
    private HttpPost postRequest;
    private HttpDelete deleteRequest;
    private static JSONObject json;
    ImgurManager manager = new ImgurManager();

    private HttpResponse httpResponse;
    String clientId;
    String token;
    String clientSecret;
    String accessToken;
    String response;
    String refreshToken;
    @BeforeClass
    public void setUp() throws IOException, ParseException, java.text.ParseException {
        client = HttpClientBuilder.create().build();
        checkDateGenerateToken();
    }

    public void checkDateGenerateToken() throws IOException, ParseException, java.text.ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH);
        Date date = new java.util.Date();
        System.out.println(date);
        dateFormat.format(date);
        Date expireDate = dateFormat.parse(new ImgurManager().getAccountData("expireDate"));

        if(expireDate.after(date)) {
            System.out.println("Token is still valid");
        }
        else{
            httpResponse=authorizationToken();
            response= EntityUtils.toString(httpResponse.getEntity());
            token = get_accessToken(response);
            refreshToken =get_refreshToken(response);

            manager.modificationJson("accessToken",token,"accountData.json");
            manager.modificationJson("refreshToken",refreshToken,"accountData.json");

            expireDate = DateUtils.addMonths(new Date(), 1);
            manager.modificationJson("expireDate",expireDate.toString(),"accountData.json");
        }
    }
    public HttpResponse authorizationToken() throws IOException, ParseException {

        clientId = manager.getAccountData("clientId");
        clientSecret = manager.getAccountData("clientSecret");
        refreshToken = manager.getAccountData("refreshToken");

        HttpResponse postRequest = Request.Post("https://api.imgur.com//oauth2/token").bodyForm(
                Form.form().add("grant_type", "refresh_token").
                        add("client_id", clientId).
                        add("client_secret", clientSecret).
                        add("refresh_token", refreshToken).build()).execute().returnResponse();

        System.out.println("postRequest " + postRequest);
        return postRequest;
    }
    public HttpResponse postImage(String imageBase64,String type,String title) throws IOException, ParseException {

        clientId = manager.getAccountData("clientId");
        accessToken = manager.getAccountData("accessToken");
        postRequest = new HttpPost("https://api.imgur.com/3/image");

        postRequest.addHeader(new BasicHeader("cookie", accessToken));
        postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        postRequest.addHeader("content-type","application/json");
        json = new JSONObject();
        json.put("image", imageBase64);
        json.put("type", type);
        json.put("title", title);
        postRequest.setEntity(new StringEntity(json.toString()));
        manager.logRequest(postRequest, "POST");
        return client.execute(postRequest);
    }
    public HttpResponse getAccountBase(String username) throws IOException, ParseException {

        clientId = manager.getAccountData("clientId");
        getRequest = new HttpGet("https://api.imgur.com/3/account/" + username);
        getRequest.addHeader(new BasicHeader("cookie", accessToken));
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
        manager.logRequest(getRequest, "Get");
        return client.execute(getRequest);
    }
    public HttpResponse getImageCount(String username) throws IOException, ParseException {

        clientId = manager.getAccountData("clientId");
        accessToken = manager.getAccountData("accessToken");
        getRequest = new HttpGet("https://api.imgur.com/3/account/" + username + "/images/count");
        getRequest.addHeader(new BasicHeader("cookie", accessToken));
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        manager.logRequest(getRequest, "GET");
        return client.execute(getRequest);
    }
    public HttpResponse deleteImageFromGallery(String imageHash) throws IOException, ParseException {
        accessToken = manager.getAccountData("accessToken");
        deleteRequest = new HttpDelete("https://api.imgur.com/3/image/" + imageHash);
        deleteRequest.addHeader(new BasicHeader("cookie", accessToken));
        deleteRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        manager.logRequest(deleteRequest, "DELETE");
        return client.execute(deleteRequest);
    }
    public HttpResponse getImageIDs(String username) throws IOException, ParseException {
        clientId = manager.getAccountData("clientId");
        accessToken = manager.getAccountData("accessToken");
        getRequest = new HttpGet("https://api.imgur.com/3/account/" + username + "/images/ids/");
        getRequest.addHeader(new BasicHeader("cookie", accessToken));
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        manager.logRequest(getRequest, "GET");
        return client.execute(getRequest);
    }
    public HttpResponse getAccountImages(String username) throws IOException, ParseException {

        clientId = manager.getAccountData("clientId");
        accessToken = manager.getAccountData("accessToken");
        getRequest = new HttpGet("https://api.imgur.com/3/account/me/images");
        getRequest.addHeader(new BasicHeader("cookie", accessToken));
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        manager.logRequest(getRequest, "GET");
        return client.execute(getRequest);
    }
}