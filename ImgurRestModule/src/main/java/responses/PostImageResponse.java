package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PostImageResponse {
    private static JSONObject restResponse;
    private static JSONObject data;
    private static JSONParser jsonParser = new JSONParser();

    public static String getStatus(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        return restResponse.get("status").toString();
    }

    public static String getAccountId(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONObject) restResponse.get("data"));
        return (data.get("account_id")).toString();
    }
    public static String getImageId(String response) throws ParseException{
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONObject) restResponse.get("data"));
        return (data.get("id")).toString();

    }
}
