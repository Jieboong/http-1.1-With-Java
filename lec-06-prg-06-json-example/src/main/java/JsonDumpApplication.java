import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class JsonDumpApplication {


    public static void main(String[] args) throws IOException, ParseException {
        JSONObject superHeroes = new JSONObject();
        superHeroes.put("squadName", "Super hero squad");
        superHeroes.put("homeTown", "Metro City");
        superHeroes.put("formed", 2016);
        superHeroes.put("secretBase", "Super tower");
        superHeroes.put("active", true);

        String[] nameList = {"Molecule Man", "Madame Uppercut", "Eternal Flame"};
        Integer[] ageList = {29, 39, 1000000};
        String[] identityList = {"Dan Jukes", "Jane Wilson", "Unknown"};
        String[][] powerList = {
                {"Radiation resistance", "Turning tiny", "Radiation blast"},
                {"Million tonne punch", "Damage resistance", "Superhumam reflexes"},
                {"Immortality", "Heat Immunity", "Inferno", "Teleportation", "Interdimensional travel"}
        };

        JSONArray members = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject member = new JSONObject();
            member.put("name", nameList[i]);
            member.put("age", ageList[i]);
            member.put("secretIdentity", identityList[i]);

            JSONArray memberPower = new JSONArray();

            for (String power : powerList[i]) {
                memberPower.add(power);
            }

            member.put("powers", memberPower);

            members.add(member);

        }
        System.out.println(members.toJSONString());

        superHeroes.put("members", members);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonWriter jsonWriter = new JsonWriter(new FileWriter("./src/main/resources/lec-06-prg-06-json-example.json"));
        jsonWriter.setIndent("    ");
        gson.toJson(superHeroes, superHeroes.getClass(), jsonWriter);

        jsonWriter.close();

        Reader reader = new FileReader("./src/main/resources/lec-06-prg-06-json-example.json");

        JSONObject heroReader = (JSONObject) (new JSONParser()).parse(reader);

        System.out.println(heroReader.get("homeTown"));

    }
}
