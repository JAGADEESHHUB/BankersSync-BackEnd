package com.bankersyncnew.newversion2O.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="client_Details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name="client_name")
    private String clientName;

    @Column(name="client_father_name")
    private String clientFatherName;

    @Column(name="client_address")
    private String clientAddress;

    @Column(name="client_primary_contact")
    private Long clientPrimaryContact;

    @Column(name="client_secondary_contact")
    private Long clientSecondaryContact;

    @Column(name="client_proof")
    private String clientProof;

    @Column(name="client_picture")
    private String clientPicture;

    @Column(name="client_created_at")
    private LocalDateTime clientCreatedAt;

    @Column(name="client_updated_at")
    private LocalDateTime clientUpdatedAt;

    @Column(name="client_records")
    private String clientRecords;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Loan> loans;
}
