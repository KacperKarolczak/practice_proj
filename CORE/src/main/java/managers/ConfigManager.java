package managers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
    private JSONParser parser = new JSONParser();
    private JSONObject jsonObject;
    private FileReader reader;

    public String getProperty(String name) throws ParseException, IOException {
        reader = new FileReader(this.getClass().getClassLoader().getResource("config.json").getFile());
        jsonObject = ((JSONObject) parser.parse(reader));
        return ((String) jsonObject.get(name));
    }


}
