package hello.controllers;

import hello.domains.User;
import hello.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity createUser(@RequestBody User user) {
        log.info("sefsefsefsf");
        return ResponseEntity.ok().body(userService.addUser(user));
    }


    @RequestMapping(value = "/get{id}", method = RequestMethod.GET)
    public  ResponseEntity getUser(@PathVariable int id){
        return  ResponseEntity.ok().body(userService.getUSer(id));
    }

    @PutMapping(value = "/changeStatus{id}")
    public  ResponseEntity changeStatus(@PathVariable("id") int id, @RequestParam("newStatus") Boolean newStatus){
        return ResponseEntity.ok().body(userService.changeStatus(id,newStatus));

    }

    @GetMapping(value = "/get")
    public @ResponseBody
    Iterable<User> getAllUsers(@RequestParam(value = "status", required = false) Boolean status) {
        return userService.getUsersByStatus(status);
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

}
