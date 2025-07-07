package com.bankersyncnew.newversion2O.Login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Login")
public class LoginController {

    @Value("${login.username}")
    private String userNameCheck;

    @Value("${login.password}")
    private String passwordCheck;

    @PostMapping("/user")
    public ResponseEntity<LoginDTO> userLogin(@RequestBody LoginDTO loginDTO){
        if(loginDTO.getUserName().equals(userNameCheck) && loginDTO.getPassword().equals(passwordCheck)){
            return ResponseEntity.ok(loginDTO);
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDTO);
    }
}
