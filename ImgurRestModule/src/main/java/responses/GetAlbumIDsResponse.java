package responses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetAlbumIDsResponse {

    private static JSONObject restResponse;
    private static JSONArray data;
    private static JSONParser jsonParser = new JSONParser();

    public static JSONArray getIDsAlbums(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONArray) restResponse.get("data"));
        return data;
    }

    public static String getStatus(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        return (restResponse .get("status")).toString();
    }


}
