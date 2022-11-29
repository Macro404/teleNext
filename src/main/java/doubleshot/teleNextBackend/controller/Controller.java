package doubleshot.teleNextBackend.controller;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    UserService service;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findUserById(@RequestHeader("web_token") String token, @PathVariable String id) {
        try {
            validateToken(token);
            return ResponseEntity.ok().body(service.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestHeader("web_token") String token, @RequestBody CreateUserDTO newUser){
        try {
            validateToken(token);
            User user = service.createUser(newUser);
            return ResponseEntity.created(URI.create("/api/users/" + user.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@RequestHeader("web_token") String token, @PathVariable String id){
        try {
            validateToken(token);
            service.deleteUser(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/phones")
    public ResponseEntity<Phone> addPhone(@RequestHeader("web_token") String token,@RequestBody PhoneDTO phone){
        try {
            validateToken(token);
            return ResponseEntity.ok().body(service.addPhone(phone));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<ProductsDTO> getProducts(){
        try{
            return ResponseEntity.status(200).body(service.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> getPhones() {
        try {

            return ResponseEntity.ok().body(service.getAllProducts().phones());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users/{id}/transactions")
    public ResponseEntity addTransaction(@RequestHeader("web_token") String token, @RequestBody OrderDTO orderDTO, @PathVariable String id) {
        try {
            System.out.println(orderDTO);
            validateToken(token);
            service.addOrder(orderDTO, id);
            return ResponseEntity.created(URI.create("/api/users/" + id + "/transactions")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/dataplans")
    public ResponseEntity<DataPlan> addDataPlan(@RequestHeader("web_token") String token, @RequestBody DataPlan plan) {
        try {
            validateToken(token);
            return ResponseEntity.ok().body(service.addDataPlan(plan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/users/{email}")
    public ResponseEntity getSubscriptionsByEmail(@RequestHeader("web_token") String token, @PathVariable String email){
        try{
            validateToken(token);
            return ResponseEntity.ok(service.getUserByEmail(email));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("users/{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity deleteSubscription(@RequestHeader("web_token") String token, @PathVariable String userId, @PathVariable String subscriptionId){
        try {
            validateToken(token);
            service.deleteSubscription(subscriptionId);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private void validateToken(String token) throws IllegalAccessException {
        if(!token.equals(System.getenv("JWT_TOKEN"))){
            throw new IllegalAccessException();
        }
    }
}
