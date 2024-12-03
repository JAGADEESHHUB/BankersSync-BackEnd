package com.banksyncfinalpack.banksync.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class User {
    
    @Id
    private String username;
    private String password;
    private String otp;

}
