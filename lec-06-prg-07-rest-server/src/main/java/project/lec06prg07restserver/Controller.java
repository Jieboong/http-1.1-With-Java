package project.lec06prg07restserver;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/membership_api")
public class Controller {
    private MembershipManager myManager = new MembershipManager();

    @PutMapping("/{memberId}")
    public JSONObject put(@PathVariable String memberId, @RequestBody JSONObject form) {
        return myManager.create(memberId, (String) form.get(memberId));
    }

    @GetMapping("/{memberId}")
    public JSONObject get(@PathVariable String memberId) {
        return myManager.read(memberId);
    }

    @PostMapping("/{memberId}")
    public JSONObject post(@PathVariable String memberId, @RequestBody JSONObject form) {
        return myManager.update(memberId, (String) form.get(memberId));
    }

    @DeleteMapping("/{memberId}")
    public JSONObject delete(@PathVariable String memberId) {
        return myManager.delete(memberId);
    }
}
