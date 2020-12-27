package com.example.springboot.data;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Ahielg
 * @date 22/12/2020
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerById(Integer id);
}