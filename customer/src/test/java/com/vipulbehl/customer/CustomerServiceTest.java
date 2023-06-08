package com.vipulbehl.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void registerCustomer() {
        CustomerRegistrationRequest request = new CustomerRegistrationRequest("John", "Doe", "john.doe@example.com");
        Customer customer = new Customer(0, "John", "Doe", "john.doe@example.com");
        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.registerCustomer(request));
    }

    @Test
    void listCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "John", "Doe", "john.doe@example.com"));
        customers.add(new Customer(2, "Jane", "Doe", "jane.doe@example.com"));
        when(customerRepository.findAll()).thenReturn(customers);

        assertEquals(customers, customerService.listCustomers());
    }

    @Test
    void getCustomerById() {
        int id = 1;
        Customer customer = new Customer(id, "John", "Doe", "john.doe@example.com");
        when(customerRepository.getById(id)).thenReturn(customer);

        assertEquals(customer, customerService.getCustomerById(id));
    }

    @Test
    void deleteCustomerById() {
        Integer id = 1;
        customerService.deleteCustomerById(id);

        // verify that the deleteById method was called once with the correct id
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(id);
    }
}