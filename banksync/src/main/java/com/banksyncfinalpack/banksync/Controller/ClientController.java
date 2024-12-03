package com.banksyncfinalpack.banksync.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banksyncfinalpack.banksync.Entity.ClientDetails;
import com.banksyncfinalpack.banksync.Service.ServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ClientController {

    @Autowired
    private ServiceImplementation serviceImplementation;
    
    // @PostMapping("addNewClient")
    // public ClientDetails addNewClient(@RequestBody ClientDetails clientDetails) {
    //     return serviceImplementation.addNewClient(clientDetails);
    // }
    private static final String UPLOAD_DIR = "./uploads/";

    @PostMapping("/addNewClient")
    public ClientDetails addNewClient(
            @RequestParam("clientDetails") String clientDetailsJson,
            @RequestParam("clientProof") MultipartFile clientProof,
            @RequestParam("clientPicture") MultipartFile clientPicture) throws IOException {

        // Convert clientDetails JSON to an object
        ObjectMapper objectMapper = new ObjectMapper();
        ClientDetails clientDetails = objectMapper.readValue(clientDetailsJson, ClientDetails.class);

        // Save files
        String proofFileName = saveFile(clientProof);
        String pictureFileName = saveFile(clientPicture);

        // Set file paths in the entity
        clientDetails.setClientProof(proofFileName);
        clientDetails.setClientPicture(pictureFileName);

        // Save client details
        return serviceImplementation.addNewClient(clientDetails);
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

    @PutMapping("editClient/{clientId}/{firstName}/{lastName}/{address}/{contactPrimary}/{contactSecondary}")
    public ClientDetails editClient(@PathVariable Long clientId, @PathVariable String name, @PathVariable String fatherName,
     @PathVariable String address, @PathVariable Long contactPrimary, @PathVariable Long contactSecondary){
    
        return serviceImplementation.editClient(clientId, name, fatherName, address, contactPrimary, contactSecondary);
    }

    @DeleteMapping("removeclient/{clientId}")
    public void removeclient(@PathVariable Long clientId) {
        serviceImplementation.removeclient( clientId );
    }

}
