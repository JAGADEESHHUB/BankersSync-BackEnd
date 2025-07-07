package com.bankersyncnew.newversion2O.service;

import com.bankersyncnew.newversion2O.dto.LoanRequestAndResponse;
import com.bankersyncnew.newversion2O.entity.Customer;
import com.bankersyncnew.newversion2O.entity.Loan;
import com.bankersyncnew.newversion2O.mapper.LoanMapper;
import com.bankersyncnew.newversion2O.repository.CustomerRepo;
import com.bankersyncnew.newversion2O.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private CustomerRepo customerRepo;

    public LoanRequestAndResponse addLoan(Long customerId, LoanRequestAndResponse loanRequestAndResponse){
        //getting
        Customer customer = customerRepo.findById(customerId).orElseThrow(()-> new RuntimeException("not found"));
        Loan loan = loanMapper.loanDtoToLoan(loanRequestAndResponse);

        //service logic
        double interestAmount = (loan.getItemInterestPercentage() / 100) * loan.getItemLoanValue();
        loan.setItemInterestValue(interestAmount);
        loan.setLoanPendingInterestAmount(interestAmount);
        loan.setLoanPendingPrincipalAmount(loan.getItemLoanValue());
        loan.setLoanPendingTotalAmount(interestAmount + loan.getItemLoanValue());

        //saving
        loan.setCustomer(customer);
        customer.getLoans().add(loan);

        return loanMapper.loanToLoanDto(loanRepo.save(loan));
    }

    public LoanRequestAndResponse updateLoan(Long id, Double interestAmount, Double principleAmount){
        Loan existingLoan = loanRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));

        existingLoan.setLoanPendingInterestAmount(existingLoan.getLoanPendingInterestAmount() - interestAmount);
        existingLoan.setLoanPendingPrincipalAmount(existingLoan.getLoanPendingPrincipalAmount() - principleAmount);
        existingLoan.setLoanPendingTotalAmount(existingLoan.getLoanPendingTotalAmount() - (interestAmount + principleAmount));

        return loanMapper.loanToLoanDto(loanRepo.save(existingLoan));
    }

    public LoanRequestAndResponse getLoan(Long id){
        Loan loan = loanRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        return loanMapper.loanToLoanDto(loan);
    }

    public List<LoanRequestAndResponse> getAllLoans(){
        List<Loan> loans=loanRepo.findAll();
        return loans.stream().map(loanMapper::loanToLoanDto).collect(Collectors.toList());
    }

    public void deleteLoan(Long id){
        Loan loan = loanRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        loanRepo.delete(loan);
    }



}
