package com.company.repository;

import com.company.domain.Customer;
import com.company.dto.request.CustomerReq;
import com.company.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final Map<Integer, Customer> customers;

    public List<Customer> getCustomers() {
        List<Customer> customerList = new ArrayList<>(customers.values());
        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            Customer customer = entry.getValue();
            customer.setId(entry.getKey());
        }
        return customerList;
    }

    public Customer getCustomerById(Integer id) {
        if (!customers.containsKey(id))
            throw new CustomerNotFoundException("Doesnt exist customer");
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customer;
    }

    public void saveCustomer(Customer customer) {
        customer.setCreatedAt(LocalDate.now());
        customer.setUpdatedAt(null);
        int id = generateNextId();
        customer.setId(id);
        customers.put(id, customer);
    }

    public Customer updateCustomer(Integer customerId, CustomerReq customerReq) {
        if (!customers.containsKey(customerId)) {
            throw new CustomerNotFoundException("Doesn`t exist customer");
        }
        Customer customer = customers.get(customerId);
        customer.setName(customerReq.getName());
        customer.setAddress(customerReq.getAddress());
        customer.setPhone(customerReq.getPhone());
        customer.setUpdatedAt(LocalDate.now());
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void deleteCustomer(Integer id) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException("Doesnt exist customer");
        } else {
            customers.remove(id);
        }
    }
    private int generateNextId() {
        return customers.size() + 1;
    }
}
