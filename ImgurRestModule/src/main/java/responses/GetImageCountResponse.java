package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetImageCountResponse {
    private static JSONObject restResponse;
    private static JSONParser jsonParser = new JSONParser();

    public static String getCount(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        return restResponse.get("data").toString();
    }
}
