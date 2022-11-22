package doubleshot.teleNextBackend.controller;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @Autowired
    UserService service;

    @GetMapping("/users/{id}")
    @CrossOrigin
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(service.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users")
    @CrossOrigin
    public ResponseEntity createUser(@RequestBody UserDTO userDto){
        try {
            User user = service.createUser(userDto);
            return ResponseEntity.created(URI.create("/api/users/" + user.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity deleteUser(@PathVariable String id){
        try {
            service.deleteUser(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/phones")
    @CrossOrigin
    public ResponseEntity addPhone(@RequestBody PhoneDTO phone){
        try {
            return ResponseEntity.ok().body(service.addPhone(phone));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    @CrossOrigin
    public ResponseEntity<ProductsDTO> getProducts(){
        return ResponseEntity.ok().body(service.getAllProducts());
    }
}
