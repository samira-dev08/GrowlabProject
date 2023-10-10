package com.company.controller;

import com.company.dto.request.CustomerReq;
import com.company.dto.response.CustomerResp;
import com.company.entity.Customer;
import com.company.exception.CustomerNotFoundException;
import com.company.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final Map<Integer, Customer> customers;
    private final CustomerMapper customerMapper;

    @GetMapping
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>(customers.values());
        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            Customer customer = entry.getValue();
            customer.setId(entry.getKey());
        }
        return customerList;
    }

    @GetMapping("/{id}")
    public CustomerResp getCustomerById(@PathVariable int id) {
        if (!customers.containsKey(id))
            throw new CustomerNotFoundException("Doesnt exist customer");
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        CustomerResp customerResp = customerMapper.toCustomerResp(customer);
        return customerResp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody @Valid CustomerReq customerReq) {
        Customer customer = customerMapper.toCustomer(customerReq);
        customer.setCreatedAt(LocalDate.now());
        customer.setUpdatedAt(null);
        int id = generateNextId();
        customer.setId(id);
        customers.put(id, customer);
    }

    @PutMapping
    public CustomerResp updateCustomer(@RequestParam(value = "customerId") Integer customerId, @RequestBody CustomerReq customerReq) {
        if (!customers.containsKey(customerId)){
            throw new CustomerNotFoundException("Doesn`t exist customer");}
        Customer customer = customers.get(customerId);
        customer.setName(customerReq.getName());
        customer.setAddress(customerReq.getAddress());
        customer.setPhone(customerReq.getPhone());
        customer.setUpdatedAt(LocalDate.now());
        customers.put(customer.getId(), customer);
        return customerMapper.toCustomerResp(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        if (!customers.containsKey(id)){
            throw new CustomerNotFoundException("Doesnt exist customer");
        }else{
            customers.remove(id);
        }

    }

    private int generateNextId() {
        return customers.size() + 1;
    }
}
