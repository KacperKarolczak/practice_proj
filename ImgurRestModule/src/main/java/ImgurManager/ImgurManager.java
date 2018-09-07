package ImgurManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ImgurManager {
    private JSONParser parser = new JSONParser();
    private JSONObject jsonObject;
    private JSONArray jsonArray = new JSONArray();
    private FileReader reader;

    public String getAccountData(String property) throws IOException, ParseException {
        reader = new FileReader(this.getClass().getClassLoader().getResource("accountData.json").getFile());
        jsonObject = ((JSONObject) parser.parse(reader));
        return ((String) jsonObject.get(property));
    }
    public String getResponsesData(String property) throws IOException, ParseException {
        reader = new FileReader(this.getClass().getClassLoader().getResource("imgurResp.json").getFile());
        jsonObject = ((JSONObject) parser.parse(reader));
        return ((String) jsonObject.get(property));
    }
    public void modificationJson(String key, String value,String jsonFile) throws ParseException {

        try{
            String path = System.getProperty("user.dir");
            File file = new File(path +"/ImgurRestModule/src/main/resources/" + jsonFile);
            jsonObject = ((JSONObject) parser.parse(new FileReader(file)));
            FileWriter fileWriter = new FileWriter(file);
            jsonObject.put(key,value);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logRequest(HttpRequestBase httpRequest, String requestMethod){
        String request = httpRequest.getURI().toString();
        System.out.println("Request: " + request);
        Reporter.log("Request: " + request);
    }
    public void logResponse(String response) throws ParseException {
        String formattedJsonString = formatJson(response);

        System.out.println("Response: " + formattedJsonString);
        Reporter.log("Response: " + formattedJsonString);
    }

    public String formatJson(String jsonString){
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        String prettyJsonString = gsonBuilder.toJson(jsonElement);
        return gsonBuilder.toJson(jsonElement);
    }
}
