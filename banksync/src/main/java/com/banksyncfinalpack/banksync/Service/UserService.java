package com.banksyncfinalpack.banksync.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.banksyncfinalpack.banksync.Entity.User;
import com.banksyncfinalpack.banksync.Repository.UserRepository;


@Service
public class UserService {

    @Value("${user.email}")
    private String email;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    //login
    public boolean login(String username, String password){
        User user=userRepository.findByUsername(username);
        return user!=null && user.getPassword().equals(password);
    }

    //send otp
    public void sendOtp(String username) {
         String otp = String.valueOf(new Random().nextInt(900000) + 100000);
         User user = userRepository.findByUsername(username);
         user.setOtp(otp);
         userRepository.save(user);

        // Send OTP via email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Password Reset");
        message.setText("Your OTP is: " + otp + "\nIt will expire in 5 minutes.");
        mailSender.send(message);
    }

    //reset
    public boolean resetPassword(String username, String otp, String newpassword){
        User user = userRepository.findByUsername(username);
        if(user!=null && otp.equals(user.getOtp())){
            user.setPassword(newpassword);
            user.setOtp(null);//after all process making otp as null
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
