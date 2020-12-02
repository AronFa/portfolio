package eu.itswc.controller;

import eu.itswc.model.User;
import eu.itswc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public User getUserbyUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @PostMapping("")
    public void adduser(@RequestBody User u) {
        userService.addUser(u);
    }

    @DeleteMapping("/{userName}")
    public void deleteuser(@PathVariable String userName){
        userService.deleteUserByUserName(userName);
    }
}
