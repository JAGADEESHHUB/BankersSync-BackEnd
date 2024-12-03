package com.banksyncfinalpack.banksync.Controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banksyncfinalpack.banksync.Entity.LoanDetails;
import com.banksyncfinalpack.banksync.Service.ServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("loan")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoanController {

    @Autowired
    private ServiceImplementation serviceImplementation;

    // @PostMapping("addNewLoan/{clientId}")
    // public LoanDetails addNewLoan(@RequestBody LoanDetails loanDetails, @PathVariable Long clientId) {
    //     return serviceImplementation.addNewLoan(loanDetails, clientId);
    // }

    private static final String UPLOAD_DIR = "./uploads/";

    @PostMapping("addNewLoan/{clientId}")
    public LoanDetails addNewLoan(
            @RequestParam("loanDetails") String loanDetailsJson,
            @RequestParam("itemPicture") MultipartFile itemPicture,
            @PathVariable Long clientId) throws IOException {

        // Convert loanDetails JSON to LoanDetails object
        ObjectMapper objectMapper = new ObjectMapper();
        LoanDetails loanDetails = objectMapper.readValue(loanDetailsJson, LoanDetails.class);

        // Save the uploaded itemPicture
        if (!itemPicture.isEmpty()) {
            String fileName = saveFile(itemPicture);
            loanDetails.setItemPicture(fileName); // Assuming setItemPicture exists in LoanDetails
        }

        // Save loanDetails with associated clientId
        return serviceImplementation.addNewLoan(loanDetails, clientId);
    }

    private String saveFile(MultipartFile file) throws IOException {
        // Ensure the directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file and return the name
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());
        return fileName;
    }

    @PutMapping("updateLoan/{loanId}/{intrestAmount}/{principleAmount}")
    public LoanDetails updateLoan(@PathVariable Long loanId, @PathVariable Double intrestAmount, @PathVariable Double principleAmount) {
        return serviceImplementation.updateLoan( loanId, intrestAmount, principleAmount);
    }

    @DeleteMapping("removeLoan/{loanId}")
    public void removeLoan(@PathVariable Long loanId) {
        serviceImplementation.removeLoan(loanId);
    }

}
