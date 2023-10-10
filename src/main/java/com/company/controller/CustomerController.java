package com.company.controller;

import com.company.dto.request.CustomerReq;
import com.company.dto.response.CustomerResp;
import com.company.domain.Customer;
import com.company.exception.CustomerNotFoundException;
import com.company.exception.CustomizedException;
import com.company.mapper.CustomerMapper;
import com.company.service.CustomerService;
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
    public final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResp getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody @Valid CustomerReq customerReq) {
        customerService.saveCustomer(customerReq);
    }

    @PutMapping
    public CustomerResp updateCustomer(@RequestParam(value = "customerId") Integer customerId, @RequestBody CustomerReq customerReq) {
        CustomerResp customerResp = customerService.updateCustomer(customerId, customerReq);
        return customerResp;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }


}
