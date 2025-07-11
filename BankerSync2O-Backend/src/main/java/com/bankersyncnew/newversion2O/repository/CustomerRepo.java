package com.bankersyncnew.newversion2O.repository;

import com.bankersyncnew.newversion2O.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
