package com.banksyncfinalpack.banksync.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banksyncfinalpack.banksync.Entity.ClientDetails;
import com.banksyncfinalpack.banksync.Entity.LoanDetails;
import com.banksyncfinalpack.banksync.Repository.ClientRepo;
import com.banksyncfinalpack.banksync.Repository.LoanRepo;

@Service
public class ServiceImplementation {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private LoanRepo loanRepo;


    public ClientDetails addNewClient(ClientDetails clientDetails) {
        return clientRepo.save(clientDetails);
    }

    public ClientDetails editClient(Long clientId, String name, String fatherName, String address,
            Long contactPrimary, Long contactSecondary) {
        ClientDetails existingClient= clientRepo.findById(clientId)
        .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        existingClient.setClientName(name);
        existingClient.setClientFatherName(fatherName);
        existingClient.setClientAddress(address);
        existingClient.setClientContactPrimary(contactPrimary);
        existingClient.setClientContactSecondary(contactSecondary);
        return existingClient;

        }

    public void removeclient(Long clientId) {
        clientRepo.deleteById(clientId);
    }

    public LoanDetails addNewLoan(LoanDetails loanDetails, Long clientId) {
        ClientDetails existingClient = clientRepo.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
            double intrestAmount = (loanDetails.getItemInterestPercentage()/100)*loanDetails.getItemLoanValue();
        loanDetails.setItemInterestValue(intrestAmount);
        loanDetails.setLoanPendingInterestAmount(intrestAmount);
        loanDetails.setLoanPendingPrincipalAmount(loanDetails.getItemLoanValue());
        loanDetails.setLoanPendingTotalAmount(intrestAmount+loanDetails.getItemLoanValue());
        loanDetails.setClientDetails(existingClient);
        return loanRepo.save(loanDetails);
    }

    public LoanDetails updateLoan(Long loanId, Double intrestAmount, Double principleAmount) {
        LoanDetails existingLoanDetails = loanRepo.findById(loanId)
        .orElseThrow(() -> new RuntimeException("Client not found with id: " + loanId));
        existingLoanDetails.setLoanPendingInterestAmount(existingLoanDetails.getLoanPendingInterestAmount()-intrestAmount);
        existingLoanDetails.setLoanPendingPrincipalAmount(existingLoanDetails.getLoanPendingPrincipalAmount()-principleAmount);
        existingLoanDetails.setLoanPendingTotalAmount(existingLoanDetails.getLoanPendingTotalAmount()-(intrestAmount+principleAmount));
        return existingLoanDetails;
    }

    public void removeLoan(Long loanId) {
        loanRepo.deleteById(loanId);
    }

    public ClientDetails getClientReport(Long clientId) {
        return clientRepo.findById(clientId)
        .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
    }

    public LoanDetails getLoanReport(Long loanId) {
       return loanRepo.findById(loanId)
       .orElseThrow(() -> new RuntimeException("Client not found with id: " + loanId));
    }

    public List<LoanDetails> getAllLoanReport() {
        return loanRepo.findAll();
    }

    public List<LoanDetails> getLast24HoursData() {
        LocalDateTime last24Hours= LocalDateTime.now().minusDays(1);
        return loanRepo.findTimeBasedData(last24Hours);
    }

    public List<LoanDetails> getLast1Month() {
        LocalDateTime last1Month= LocalDateTime.now().minusMonths(1);
        return loanRepo.findTimeBasedData(last1Month);
    }

    public List<LoanDetails> getLast3Month() {
        LocalDateTime last3Month= LocalDateTime.now().minusMonths(3);
        return loanRepo.findTimeBasedData(last3Month);
    }

    public List<LoanDetails> getLast1Year() {
        LocalDateTime last1Year= LocalDateTime.now().minusYears(1);
        return loanRepo.findTimeBasedData(last1Year);
    }

}
