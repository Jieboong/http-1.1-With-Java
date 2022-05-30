package project.lec06prg07restserver;

import java.util.HashMap;

public class MembershipManager {
    private HashMap<String, String> database;

    public HashMap<String, String> create(String id, String value) {
        HashMap<String, String> result = new HashMap<>();

        if (database.containsKey(id)) {
            result.put(id, "None");
        } else {
            this.database.put(id, value);
            result.put(id, this.database.get(id));
        }

        return result;
    }

    public HashMap<String, String> read(String id) {
        HashMap<String, String> result = new HashMap<>();
        if (database.containsKey(id)) {
            result.put(id, this.database.get(id));
        } else {
            result.put(id, "None");
        }
        return result;
    }

    public HashMap<String, String> update(String id, String value) {
        HashMap<String, String> result = new HashMap<>();
        if (database.containsKey(id)) {
            this.database.put(id, value);
            result.put(id, database.get(id));
        }else{
            result.put(id, "None");
        }
        return result;
    }

    public HashMap<String, String> delete(String id) {
        HashMap<String, String> result = new HashMap<>();
        if (database.containsKey(id)) {
            database.remove(id);
            result.put(id, "Removed");
        } else {
            result.put(id, "None");
        }
        return result;
    }

    public MembershipManager(){
        this.database = new HashMap<>();
    }
}
