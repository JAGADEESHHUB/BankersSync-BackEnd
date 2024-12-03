package com.banksyncfinalpack.banksync.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banksyncfinalpack.banksync.Entity.ClientDetails;
import com.banksyncfinalpack.banksync.Entity.LoanDetails;
import com.banksyncfinalpack.banksync.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("reports")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ReportsController {

    @Autowired
    private ServiceImplementation serviceImplementation;


    @GetMapping("clientReport/{clientId}")
    public ClientDetails clientReport(@PathVariable Long clientId) {
        return serviceImplementation.getClientReport(clientId);
    }

    @GetMapping("loanReport/{loanId}")
    public LoanDetails loanReport(@PathVariable Long loanId) {
        return serviceImplementation.getLoanReport(loanId);  
    }

    @GetMapping("allLoanReport")
    public List<LoanDetails> allLoanReport() {
        return serviceImplementation.getAllLoanReport();
    }
    
}
