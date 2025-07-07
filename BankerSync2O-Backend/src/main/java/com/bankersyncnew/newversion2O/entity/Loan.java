package com.bankersyncnew.newversion2O.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="loan_Details")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name="owner_name")
    private String ownerName;

    @Column(name="owner_contact_number")
    private Long ownerContactNumber;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="item_picture")
    private String itemPicture;

    @Column(name="item_value")
    private Double itemValue;

    @Column(name="item_loan_value")
    private Double itemLoanValue;

    @Column(name="item_interest_percentage")
    private Double itemInterestPercentage;

    @Column(name="item_interest_period")
    private Long itemInterestPeriod;

    @Column(name="item_interest_value")
    private Double itemInterestValue;

    @Column(name="item_loan_date")
    private LocalDateTime itemLoanDate;

    @Column(name="item_return_date")
    private LocalDateTime itemReturnDate;

    @Column(name="loan_pending_interest_amount")
    private Double loanPendingInterestAmount;

    @Column(name="loan_pending_principal_amount")
    private Double loanPendingPrincipalAmount;

    @Column(name="loan_pending_total_amount")
    private Double loanPendingTotalAmount;

    @Column(name="item_status")
    private String itemStatus;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @JsonBackReference
    private Customer customer;

}
