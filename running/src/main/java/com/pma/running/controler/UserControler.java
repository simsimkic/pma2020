package com.pma.running.controler;

import com.pma.running.dto.LoginRequest;
import com.pma.running.model.User;
import com.pma.running.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControler {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
        return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody LoginRequest loginDTO) throws Exception {
        return new ResponseEntity<User>(userService.login(loginDTO), HttpStatus.OK);
    }
}
