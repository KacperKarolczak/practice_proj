package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetAccountBaseResponse {
    private static JSONObject restResponse;
    private static JSONObject data;
    private static JSONParser jsonParser = new JSONParser();

    public static String getUrl(String response) throws ParseException {
    restResponse = ((JSONObject)jsonParser.parse(response));
    data = ((JSONObject)restResponse.get("data"));
    return (data.get("url")).toString();
    }
    public static String getId(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        data = ((JSONObject)restResponse.get("data"));
        return (data.get("id")).toString();
    }
    public static String getAvatar_name(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        data = ((JSONObject)restResponse.get("data"));
        return (data.get("avatar_name")).toString();
    }
}
