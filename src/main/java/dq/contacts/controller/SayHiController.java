package dq.contacts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
public class SayHiController {

    @GetMapping("/greeting")
    public String greet() {
        return "Hello Word from docker-compose";
    }
}
