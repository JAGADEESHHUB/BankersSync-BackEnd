package com.banksyncfinalpack.banksync.Entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "clientDetails")
public class ClientDetails {
    @Id
    private Long clientId;

    private String clientName;
    private String clientFatherName;
    private String clientAddress;
    private Long clientContactPrimary;
    private Long clientContactSecondary;
    private String clientProof;
    private String clientPicture;
    private Date clientCreatedAt;
    private Date clientUpdatedAt; 

    @OneToMany(mappedBy = "clientDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<LoanDetails> loanDetailsList;

}

