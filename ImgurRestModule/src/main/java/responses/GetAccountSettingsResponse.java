package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetAccountSettingsResponse {
    private static JSONObject restResponse;
    private static JSONObject data;
    private static JSONParser jsonParser = new JSONParser();

    public static String getUrll(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        data = ((JSONObject)restResponse.get("data"));
        return (data.get("account_url")).toString();
    }
}
