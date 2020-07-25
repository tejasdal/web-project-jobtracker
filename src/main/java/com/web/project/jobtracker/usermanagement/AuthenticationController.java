package com.web.project.jobtracker.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Parth Bagaria
 *
 * Banner ID: B00839783
 */
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping(value = "/updateUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user) {
        userManagerService.updateUser(user);
    }

    @PostMapping(value = "/newUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newUser(@RequestBody NewUser user) {
        userManagerService.newUser(user);
    }
}
