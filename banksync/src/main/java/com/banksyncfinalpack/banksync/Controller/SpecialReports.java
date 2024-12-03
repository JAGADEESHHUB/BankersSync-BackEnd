package com.banksyncfinalpack.banksync.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banksyncfinalpack.banksync.Entity.LoanDetails;
import com.banksyncfinalpack.banksync.Service.ServiceImplementation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("specialReports")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class SpecialReports {

    @Autowired
    private ServiceImplementation serviceImplementation;

    @GetMapping("24Hours")
    public List<LoanDetails> getLast24HoursData() {
        return serviceImplementation.getLast24HoursData();
    }

    @GetMapping("1Month")
    public List<LoanDetails> getLast1Month() {
        return serviceImplementation.getLast1Month();
    }

    @GetMapping("3Month")
    public List<LoanDetails> getLast3Month() {
        return serviceImplementation.getLast3Month();
    }

    @GetMapping("1Year")
    public List<LoanDetails> getLast1Year() {
        return serviceImplementation.getLast1Year();
    }
    

}
