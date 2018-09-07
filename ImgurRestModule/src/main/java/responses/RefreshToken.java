package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RefreshToken {

    private static JSONObject restResponse;
    private static JSONParser jsonParser = new JSONParser();

    public static String get_accessToken(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));//jesli coś nie tak zobaczyc strukture pliku
        //data = ((JSONObject)restResponse.get("data"));
        return (restResponse.get("access_token")).toString();
    }

    public static String get_refreshToken(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        //data = ((JSONObject)restResponse.get("data"));
        return (restResponse.get("refresh_token")).toString();
    }
}
