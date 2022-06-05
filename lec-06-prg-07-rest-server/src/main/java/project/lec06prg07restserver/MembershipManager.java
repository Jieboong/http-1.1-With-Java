package project.lec06prg07restserver;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class MembershipManager {
    private JSONObject database;

    public JSONObject create(String id, String value) {
        JSONObject result = new JSONObject();

        if (database.containsKey(id)) {
            result.put(id, "None");
        } else {
            this.database.put(id, value);
            result.put(id, this.database.get(id));
        }

        return result;
    }

    public JSONObject read(String id) {
        JSONObject result = new JSONObject();
        if (database.containsKey(id)) {
            result.put(id, this.database.get(id));
        } else {
            result.put(id, "None");
        }
        return result;
    }

    public JSONObject update(String id, String value) {
        JSONObject result = new JSONObject();
        if (database.containsKey(id)) {
            this.database.put(id, value);
            result.put(id, database.get(id));
        }else{
            result.put(id, "None");
        }
        return result;
    }

    public JSONObject delete(String id) {
        JSONObject result = new JSONObject();
        if (database.containsKey(id)) {
            database.remove(id);
            result.put(id, "Removed");
        } else {
            result.put(id, "None");
        }
        return result;
    }

    public MembershipManager(){
        this.database = new JSONObject();
    }
}
