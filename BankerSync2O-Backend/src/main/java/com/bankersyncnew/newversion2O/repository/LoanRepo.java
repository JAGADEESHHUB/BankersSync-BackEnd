package com.bankersyncnew.newversion2O.repository;

import com.bankersyncnew.newversion2O.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

}
