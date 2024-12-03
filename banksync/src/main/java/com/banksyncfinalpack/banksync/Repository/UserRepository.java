package com.banksyncfinalpack.banksync.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banksyncfinalpack.banksync.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
