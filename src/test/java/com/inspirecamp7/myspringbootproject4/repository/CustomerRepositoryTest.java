package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testInsert() {
        //Given-When-Then
        Customer customer = new Customer();
        customer.setCustomerId("AB001");
        customer.setCustomerName("스프링AB");

        Customer addedCustomer = customerRepository.save(customer);

        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("AB001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링AB");

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testUpdate() {
        Customer customer = customerRepository.findByCustomerId("AB001")
                .orElseGet(() -> new Customer());
        customer.setCustomerName("SpringAB");
//        Customer updatedCustomer = customerRepository.save(customer);
//        assertThat(updatedCustomer.getCustomerName()).isEqualTo("SpringB");
        assertThat(customer.getCustomerName()).isEqualTo("SpringAB");
    }

    @Test
    @Transactional(readOnly = true)
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B001");
        if (optionalCustomer.isPresent()) {
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getCustomerId()).isEqualTo("B001");
        }
        optionalCustomer.ifPresent(customer -> System.out.println(customer.getCustomerName()));
    }

    @Test
    void testReadNotFound() {
        //Optional orElseGet(Supplier supplier)
        //Supplier의 추상메서드 T get()
        Customer customer = customerRepository.findByCustomerId("F001")
                .orElseGet(() -> new Customer());
        assertThat(customer.getCustomerId()).isNull();

    }

    @Test
    void testReadNotFoundException() {
        Customer notExistCustomer = customerRepository.findByCustomerId("B001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        assertThat(notExistCustomer.getCustomerId()).isEqualTo("B001");
    }
}