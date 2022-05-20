import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriteApplication {
    public static void main(String[] args) throws IOException {
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

        System.out.println(superHeroes.get("homeTown"));
        System.out.println(superHeroes.get("active"));
        JSONArray members1 = (JSONArray) superHeroes.get("members");
        JSONArray powers = (JSONArray) ((JSONObject) members1.get(1)).get("powers");
        System.out.println(powers.get(2));

        FileWriter fileWriter = new FileWriter("src/main/java/data/lec-06-prg-04-json-example.json");
        fileWriter.write(superHeroes.toJSONString());
        fileWriter.flush();
    }
}
