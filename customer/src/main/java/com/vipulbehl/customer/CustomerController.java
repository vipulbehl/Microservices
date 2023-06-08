package com.vipulbehl.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public Customer registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("Received a customer register request");
        return customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping
    public List<Customer> listCustomers() {
        log.info("Received a list customers requests");
        return customerService.listCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        log.info("Received a get customer by id" + id);
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        log.info("Received delete request for customer id - " + id);
        customerService.deleteCustomerById(id);
    }
}
