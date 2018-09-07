package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetCommentResponse {
        private static JSONObject restResponse;
        private static JSONObject data;
        private static JSONParser jsonParser = new JSONParser();

        public static String getCommentId(String response) throws ParseException {
            restResponse = ((JSONObject) jsonParser.parse(response));
            data = (JSONObject)restResponse.get("data");
            return (data.get("id")).toString();
        }

        public static String getComment(String response) throws ParseException{
            restResponse = ((JSONObject) jsonParser.parse(response));
            data = (JSONObject)restResponse.get("data");
            return (data.get("comment")).toString();
        }
    }


