package doubleshot.teleNextBackend.controller;

import doubleshot.teleNextBackend.model.User;
import doubleshot.teleNextBackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(service.getUserById());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){

    }
}
