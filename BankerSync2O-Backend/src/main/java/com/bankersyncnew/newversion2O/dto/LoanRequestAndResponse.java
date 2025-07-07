package com.bankersyncnew.newversion2O.dto;

import com.bankersyncnew.newversion2O.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRequestAndResponse {
    private Long loanId;
    private String ownerName;
    private Long ownerContactNumber;
    private LocalDateTime createdDate;
    private String itemPicture;
    private Double itemValue;
    private Double itemLoanValue;
    private Double itemInterestPercentage;
    private Long itemInterestPeriod;
    private Double itemInterestValue;
    private LocalDateTime itemLoanDate;
    private LocalDateTime itemReturnDate;
    private Double loanPendingInterestAmount;
    private Double loanPendingPrincipalAmount;
    private Double loanPendingTotalAmount;
    private String itemStatus;
    private Customer customer;
}
