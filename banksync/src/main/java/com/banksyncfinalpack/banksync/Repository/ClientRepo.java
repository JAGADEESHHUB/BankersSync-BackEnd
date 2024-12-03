package com.banksyncfinalpack.banksync.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banksyncfinalpack.banksync.Entity.ClientDetails;


@Repository
public interface ClientRepo extends JpaRepository<ClientDetails, Long> {

}
