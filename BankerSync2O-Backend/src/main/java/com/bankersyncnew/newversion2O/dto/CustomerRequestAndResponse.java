package com.bankersyncnew.newversion2O.dto;

import com.bankersyncnew.newversion2O.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequestAndResponse {
    private Long clientId;
    private String clientName;
    private String clientFatherName;
    private String clientAddress;
    private Long clientPrimaryContact;
    private Long clientSecondaryContact;
    private String clientProof;
    private String clientPicture;
    private LocalDateTime clientCreatedAt;
    private LocalDateTime clientUpdatedAt;
    private String clientRecords;
    private List<Loan> loans;
}
