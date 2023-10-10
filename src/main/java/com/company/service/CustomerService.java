package com.company.service;

import com.company.domain.Customer;
import com.company.dto.request.CustomerReq;
import com.company.dto.response.CustomerResp;
import com.company.mapper.CustomerMapper;
import com.company.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    public final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public List<Customer> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    public CustomerResp getCustomerById(Integer id) {
        Customer customer = customerRepository.getCustomerById(id);
        CustomerResp customerResp = customerMapper.toCustomerResp(customer);
        return customerResp;
    }

    public void saveCustomer(CustomerReq customerReq) {
        Customer customer = customerMapper.toCustomer(customerReq);
        customerRepository.saveCustomer(customer);
    }

    public CustomerResp updateCustomer(Integer customerId,CustomerReq customerReq) {
        Customer customer=customerRepository.updateCustomer(customerId,customerReq);
        return customerMapper.toCustomerResp(customer);

    }

    public void deleteCustomer(Integer id) {
         customerRepository.deleteCustomer(id);
    }
}
