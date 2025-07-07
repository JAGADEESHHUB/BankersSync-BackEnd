package com.bankersyncnew.newversion2O.controller;

import com.bankersyncnew.newversion2O.dto.CustomerRequestAndResponse;
import com.bankersyncnew.newversion2O.service.CustomerService;
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
@RequestMapping("/customerMain")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerRequestAndResponse> addCustomer(@ModelAttribute CustomerRequestAndResponse customerRequestAndResponse, @RequestParam MultipartFile proofFile, @RequestParam MultipartFile pictureFile) throws IOException {

        if (proofFile != null && !proofFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(proofFile.getBytes(), ObjectUtils.emptyMap());
            customerRequestAndResponse.setClientProof(uploadResult.get("url").toString());
        }

        if (pictureFile != null && !pictureFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(pictureFile.getBytes(), ObjectUtils.emptyMap());
            customerRequestAndResponse.setClientPicture(uploadResult.get("url").toString());
        }

        CustomerRequestAndResponse customerRequestAndResponse1 = customerService.addCustomer(customerRequestAndResponse);
        return ResponseEntity.ok(customerRequestAndResponse1);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerRequestAndResponse> updateCustomer(@PathVariable Long id, @ModelAttribute CustomerRequestAndResponse customerRequestAndResponse, @RequestParam(required = false) MultipartFile proofFile, @RequestParam(required = false) MultipartFile pictureFile) throws IOException {

        if (proofFile != null && !proofFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(proofFile.getBytes(), ObjectUtils.emptyMap());
            customerRequestAndResponse.setClientProof(uploadResult.get("url").toString());
        }

        if (pictureFile != null && !pictureFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(pictureFile.getBytes(), ObjectUtils.emptyMap());
            customerRequestAndResponse.setClientPicture(uploadResult.get("url").toString());
        }

        CustomerRequestAndResponse customerRequestAndResponse1 = customerService.updateCustomer(id,customerRequestAndResponse);
        return ResponseEntity.ok(customerRequestAndResponse1);
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerRequestAndResponse> getCustomer(@PathVariable Long id){
        CustomerRequestAndResponse customerRequestAndResponse = customerService.getCustomer(id);
        return ResponseEntity.ok(customerRequestAndResponse);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerRequestAndResponse>> getAllCustomers(){
        List<CustomerRequestAndResponse> customerRequestAndResponses=customerService.getAllCustomers();
        return ResponseEntity.ok(customerRequestAndResponses);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Deleted successfully");
    }


}

