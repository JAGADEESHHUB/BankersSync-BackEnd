package com.bankersyncnew.newversion2O.controller;

import com.bankersyncnew.newversion2O.dto.LoanRequestAndResponse;
import com.bankersyncnew.newversion2O.entity.Loan;
import com.bankersyncnew.newversion2O.service.LoanService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loanMain")
public class LoanController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private LoanService loanService;

    @PostMapping("/addLoan/{customerId}")
    public ResponseEntity<LoanRequestAndResponse> addLoan(@PathVariable Long customerId, @ModelAttribute LoanRequestAndResponse loanRequestAndResponse, @RequestParam(required = false) MultipartFile itemPictureFile) throws IOException {

        if(itemPictureFile!=null && itemPictureFile.isEmpty()){
            Map uploadResult = cloudinary.uploader().upload(itemPictureFile.getBytes(), ObjectUtils.emptyMap());
            loanRequestAndResponse.setItemPicture(uploadResult.get("url").toString());
        }
        return ResponseEntity.ok(loanService.addLoan(customerId, loanRequestAndResponse));
    }

    @PutMapping("/updateLoan/{id}/{interestAmount}/{principleAmount}")
    public ResponseEntity<LoanRequestAndResponse> updateLoan(@PathVariable Long id, @PathVariable Double interestAmount, @PathVariable Double principleAmount){
        return ResponseEntity.ok(loanService.updateLoan(id,interestAmount, principleAmount));
    }

    @GetMapping("/getLoan/{id}")
    public ResponseEntity<LoanRequestAndResponse> getLoan(@PathVariable Long id){
        return ResponseEntity.ok(loanService.getLoan(id));
    }

    @GetMapping("/getAllLoans")
    public ResponseEntity<List<LoanRequestAndResponse>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @DeleteMapping("/deleteLoan/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
