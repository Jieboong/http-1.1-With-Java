package project.lec06prg07restserver;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController("/membership_api")
public class Controller {
    private MembershipManager myManager = new MembershipManager();

    @PutMapping("/{memberId}")
    public HashMap<String, String> put(@PathVariable String memberId, @RequestBody HashMap<String, String> form) {
        return myManager.create(memberId, form.get(memberId));
    }

    @GetMapping("/{memberId}")
    public HashMap<String, String> get(@PathVariable String memberId) {
        return myManager.read(memberId);
    }

    @PostMapping("/{memberId}")
    public HashMap<String, String> post(@PathVariable String memberId, @RequestBody HashMap<String, String> form) {
        return myManager.update(memberId, form.get(memberId));
    }

    @DeleteMapping("/{memberId}")
    public HashMap<String, String> delete(@PathVariable String memberId) {
        return myManager.delete(memberId);
    }
}
