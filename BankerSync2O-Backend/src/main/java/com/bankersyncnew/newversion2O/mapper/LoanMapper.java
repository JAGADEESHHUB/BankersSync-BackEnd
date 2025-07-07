package com.bankersyncnew.newversion2O.mapper;

import com.bankersyncnew.newversion2O.dto.CustomerRequestAndResponse;
import com.bankersyncnew.newversion2O.dto.LoanRequestAndResponse;
import com.bankersyncnew.newversion2O.entity.Customer;
import com.bankersyncnew.newversion2O.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanRequestAndResponse loanToLoanDto(Loan loan){
        return new LoanRequestAndResponse(
                loan.getLoanId(),
                loan.getOwnerName(),
                loan.getOwnerContactNumber(),
                loan.getCreatedDate(),
                loan.getItemPicture(),
                loan.getItemValue(),
                loan.getItemLoanValue(),
                loan.getItemInterestPercentage(),
                loan.getItemInterestPeriod(),
                loan.getItemInterestValue(),
                loan.getItemLoanDate(),
                loan.getItemReturnDate(),
                loan.getLoanPendingInterestAmount(),
                loan.getLoanPendingPrincipalAmount(),
                loan.getLoanPendingTotalAmount(),
                loan.getItemStatus(),
                loan.getCustomer()
        );
    }

    public Loan loanDtoToLoan(LoanRequestAndResponse loanRequestAndResponse){
        return new Loan(

                loanRequestAndResponse.getLoanId(),
                loanRequestAndResponse.getOwnerName(),
                loanRequestAndResponse.getOwnerContactNumber(),
                loanRequestAndResponse.getCreatedDate(),
                loanRequestAndResponse.getItemPicture(),
                loanRequestAndResponse.getItemValue(),
                loanRequestAndResponse.getItemLoanValue(),
                loanRequestAndResponse.getItemInterestPercentage(),
                loanRequestAndResponse.getItemInterestPeriod(),
                loanRequestAndResponse.getItemInterestValue(),
                loanRequestAndResponse.getItemLoanDate(),
                loanRequestAndResponse.getItemReturnDate(),
                loanRequestAndResponse.getLoanPendingInterestAmount(),
                loanRequestAndResponse.getLoanPendingPrincipalAmount(),
                loanRequestAndResponse.getLoanPendingTotalAmount(),
                loanRequestAndResponse.getItemStatus(),
                loanRequestAndResponse.getCustomer()
        );
    }

}
