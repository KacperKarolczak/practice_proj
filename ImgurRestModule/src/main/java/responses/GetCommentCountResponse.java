package responses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class GetCommentCountResponse {
    private static JSONObject restResponse;
    private static JSONObject commentsIdList;
    private static JSONObject authorsList;
    private static JSONParser jsonParser = new JSONParser();
    private static JSONArray data;

    public static ArrayList<String> getCommentsIdlist(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONArray) restResponse.get("data"));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            commentsIdList = ((JSONObject) jsonParser.parse(data.get(i).toString()));
            list.add(commentsIdList.get("id").toString());
        }

        return list;
    }

    public static ArrayList<String> getCommentAuthor(String response) throws ParseException {
        restResponse = ((JSONObject) jsonParser.parse(response));
        data = ((JSONArray) restResponse.get("data"));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            authorsList = ((JSONObject) jsonParser.parse(data.get(i).toString()));
            list.add(commentsIdList.get("author").toString());
        }
        return list;
    }

}