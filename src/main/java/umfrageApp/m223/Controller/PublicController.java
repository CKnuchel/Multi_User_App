package umfrageApp.m223.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/public")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello Public World!");
    }

}
