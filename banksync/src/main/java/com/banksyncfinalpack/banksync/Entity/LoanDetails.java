package com.banksyncfinalpack.banksync.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class LoanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date itemLoanDate;
    private Date itemReturnDate;
    private Double loanPendingInterestAmount;
    private Double loanPendingPrincipalAmount;
    private Double loanPendingTotalAmount;
    private String itemStatus;

    @ManyToOne
    @JoinColumn(name="clientDetails_id")
    @JsonBackReference
    private ClientDetails clientDetails;

}
