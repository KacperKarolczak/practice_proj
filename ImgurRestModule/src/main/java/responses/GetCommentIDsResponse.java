package responses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class GetCommentIDsResponse {
    private static JSONObject restResponse;
    private static JSONArray data;
    private static JSONParser jsonParser = new JSONParser();

    public static ArrayList<String> getCommentsIDsList(String response) throws ParseException{
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONArray) restResponse.get("data"));
        return (ArrayList<String>) data;
    }
    }
