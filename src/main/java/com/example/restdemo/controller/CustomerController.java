package com.example.restdemo.controller;

import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.model.Customer;
import com.example.restdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("customers")
    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Get failed for:" + customerId));

        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.customerRepository.save(customer);
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<Customer> updateCustomerById(
            @PathVariable(value = "id") Long customerId,
            @RequestBody Customer newCustomer
    ) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Edit failed for:" + customerId));

        customer.setName(newCustomer.getName());
        customer.setEmail(newCustomer.getEmail());

        return ResponseEntity.ok(this.customerRepository.save(customer));
    }

    @DeleteMapping("customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Delete failed for:" + customerId));

        this.customerRepository.delete(customer);

        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}

