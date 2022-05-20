import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonExampleApplication {

    public static void main(String[] args) throws IOException, ParseException {

        Reader reader = new FileReader("src/main/java/data/lec-06-prg-03-json-example.json");
        JSONObject superHeroes = (JSONObject) (new JSONParser()).parse(reader);

        System.out.println(superHeroes.get("homeTown"));
        System.out.println(superHeroes.get("active"));

        List<JSONObject> memberList = new ArrayList<>();

        for (Object member : (JSONArray) superHeroes.get("members")) {
            memberList.add((JSONObject) member);
        }

        List<String> powers = (List<String>) memberList.get(1).get("powers");
        System.out.println(powers.get(2));

    }
}
