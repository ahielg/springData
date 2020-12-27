package com.example.springboot.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author Ahielg
 * @date 27/12/2020
 */
@Slf4j
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public void saveAndLog(Book book) {
        log.info("saving the book entity: {}", book);
        entityManager.persist(book);
    }
}
