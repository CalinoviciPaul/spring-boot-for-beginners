package com.in28minutes.springboot.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by IrianLaptop on 7/7/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
