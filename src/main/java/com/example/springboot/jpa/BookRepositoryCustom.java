package com.example.springboot.jpa;

/**
 * @author Ahielg
 * @date 27/12/2020
 */
public interface BookRepositoryCustom {
    void saveAndLog(Book book);
}
