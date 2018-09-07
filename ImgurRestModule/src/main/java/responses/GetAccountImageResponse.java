package responses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class GetAccountImageResponse {
    private static JSONObject restResponse;
    private static JSONArray data;
    private static JSONObject data2;
    private static JSONObject idList;
    private static JSONParser jsonParser = new JSONParser();

    public static ArrayList<String> getImageInformationsIdlist(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONArray) restResponse.get("data"));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i<data.size();i++) {
            idList = ((JSONObject) jsonParser.parse( data.get(i).toString()));
            list.add(idList.get("id").toString());
        }
        return list;
    }

    public static String getAccountID(String response) throws ParseException {
        restResponse = ((JSONObject)jsonParser.parse(response));
        data2 = ((JSONObject)restResponse.get("data"));
        return (data2.get("account_id")).toString();
    }

    public static String getImgID(String response) throws ParseException {
        restResponse=((JSONObject)jsonParser.parse(response));
        data2=((JSONObject)restResponse.get("data"));
        return (data2.get("id")).toString();
    }
}