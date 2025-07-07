package com.bankersyncnew.newversion2O.service;

import com.bankersyncnew.newversion2O.dto.CustomerRequestAndResponse;
import com.bankersyncnew.newversion2O.entity.Customer;
import com.bankersyncnew.newversion2O.mapper.CustomerMapper;
import com.bankersyncnew.newversion2O.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerRequestAndResponse addCustomer(CustomerRequestAndResponse customerRequestAndResponse){
        Customer customer = customerMapper.customerDtoToCustomer(customerRequestAndResponse);
        Customer customer1=customerRepo.save(customer);
        return customerMapper.customerToCustomerDto(customer1);
    }

    public CustomerRequestAndResponse updateCustomer(Long id, CustomerRequestAndResponse customerRequestAndResponse){
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));

        if(customerRequestAndResponse.getClientName()!=null)customer.setClientName(customerRequestAndResponse.getClientName());
        if(customerRequestAndResponse.getClientFatherName()!=null)customer.setClientFatherName(customerRequestAndResponse.getClientFatherName());
        if(customerRequestAndResponse.getClientAddress()!=null)customer.setClientAddress(customerRequestAndResponse.getClientAddress());
        if(customerRequestAndResponse.getClientPrimaryContact()!=null)customer.setClientPrimaryContact(customerRequestAndResponse.getClientPrimaryContact());
        if(customerRequestAndResponse.getClientSecondaryContact()!=null)customer.setClientSecondaryContact(customerRequestAndResponse.getClientSecondaryContact());
        if(customerRequestAndResponse.getClientProof()!=null)customer.setClientProof(customerRequestAndResponse.getClientProof());
        if(customerRequestAndResponse.getClientPicture()!=null)customer.setClientPicture(customerRequestAndResponse.getClientPicture());
        if(customerRequestAndResponse.getClientCreatedAt()!=null)customer.setClientCreatedAt(customerRequestAndResponse.getClientCreatedAt());
        if(customerRequestAndResponse.getClientUpdatedAt()!=null)customer.setClientUpdatedAt(customerRequestAndResponse.getClientUpdatedAt());
        if(customerRequestAndResponse.getClientRecords()!=null)customer.setClientRecords(customerRequestAndResponse.getClientRecords());

        Customer customer1 = customerRepo.save(customer);
        return customerMapper.customerToCustomerDto(customer1);

    }

    public CustomerRequestAndResponse getCustomer(Long id){
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        return customerMapper.customerToCustomerDto(customer);
    }

    public List<CustomerRequestAndResponse> getAllCustomers(){
        List<Customer> customers = customerRepo.findAll();
        return customers.stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        customerRepo.delete(customer);
    }

}
