package com.bankersyncnew.newversion2O.mapper;


import com.bankersyncnew.newversion2O.dto.CustomerRequestAndResponse;
import com.bankersyncnew.newversion2O.entity.Customer;
import com.bankersyncnew.newversion2O.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerRequestAndResponse customerToCustomerDto(Customer customer){
        return new CustomerRequestAndResponse(
                customer.getClientId(),
                customer.getClientName(),
                customer.getClientFatherName(),
                customer.getClientAddress(),
                customer.getClientPrimaryContact(),
                customer.getClientSecondaryContact(),
                customer.getClientProof(),
                customer.getClientPicture(),
                customer.getClientCreatedAt(),
                customer.getClientUpdatedAt(),
                customer.getClientRecords(),
                customer.getLoans()
        );
    }

    public Customer customerDtoToCustomer(CustomerRequestAndResponse customerRequestAndResponse){
        return new Customer(
                customerRequestAndResponse.getClientId(),
                customerRequestAndResponse.getClientName(),
                customerRequestAndResponse.getClientFatherName(),
                customerRequestAndResponse.getClientAddress(),
                customerRequestAndResponse.getClientPrimaryContact(),
                customerRequestAndResponse.getClientSecondaryContact(),
                customerRequestAndResponse.getClientProof(),
                customerRequestAndResponse.getClientPicture(),
                customerRequestAndResponse.getClientCreatedAt(),
                customerRequestAndResponse.getClientUpdatedAt(),
                customerRequestAndResponse.getClientRecords(),
                customerRequestAndResponse.getLoans()
        );
    }
}
