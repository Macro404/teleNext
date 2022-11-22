package doubleshot.teleNextBackend.controller;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    UserService service;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(service.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody UserDTO userDto){
        try {
            User user = service.createUser(userDto);
            return ResponseEntity.created(URI.create("/api/users/" + user.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        try {
            service.deleteUser(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/phones")
    public ResponseEntity<Phone> addPhone(@RequestBody PhoneDTO phone){
        try {
            return ResponseEntity.ok().body(service.addPhone(phone));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProductsDTO> getProducts(){
        try{
            return ResponseEntity.status(200).body(service.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
