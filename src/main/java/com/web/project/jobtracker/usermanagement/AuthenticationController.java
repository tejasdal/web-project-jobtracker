package com.web.project.jobtracker.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private IUserManagerService userManagerService;

    @GetMapping(value = "/getUser/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        System.out.println("worked");
        User user = null;
        if (email != null) {
            user = userManagerService.getUser(email);
        }
//        System.out.println(user.getFirstName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (user != null) {
            userManagerService.updateUser(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("success");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("failed");
    }

}
