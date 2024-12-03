package com.banksyncfinalpack.banksync.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banksyncfinalpack.banksync.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class UserAuth {

    @Autowired
    private UserService userService;
    

    @PostMapping("/login")
    public String postMethodName(@RequestParam String username, @RequestParam String password ) {
        if(userService.login(username, password)){
            return "login sucessful";
        }else{
            return "invalid password or username";
        }
    }

    @PostMapping("resetpassword/sendOtp")
    public String sendOtp(@RequestParam String username) {
        try{
            userService.sendOtp(username);
            return "Otp sent sucessfully";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("resetpassword")
    public String resetPassword(@RequestParam String username, @RequestParam String otp, @RequestParam String newPassword) {
        if(userService.resetPassword(username, otp, newPassword)){
            return "reset sucessful";
        }else{
            return "invalid otp";
        }
        
    } 

}
