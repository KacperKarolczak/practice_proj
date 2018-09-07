package responses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommentCreationResponse {

        private static JSONObject restResponse;
        private static JSONParser jsonParser = new JSONParser();

        public static String getStatus(String response) throws ParseException {
            restResponse = ((JSONObject)jsonParser.parse(response));
            return restResponse.get("status").toString();
        }

    }
