package com.banksyncfinalpack.banksync.Repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banksyncfinalpack.banksync.Entity.LoanDetails;


@Repository
public interface LoanRepo extends JpaRepository<LoanDetails, Long> {

    @Query("SELECT e FROM LoanDetails e WHERE e.createdDate >= :fromTime")
    List<LoanDetails> findTimeBasedData(@Param("fromTime") LocalDateTime fromTime);


}
