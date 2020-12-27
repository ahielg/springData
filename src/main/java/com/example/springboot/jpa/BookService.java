package com.example.springboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahielg
 * @date 22/12/2020
 */
@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public void save(Book book) {
        this.repo.save(book);
    }

    public List<Book> findByInCriteria(List<String> names) {
        return repo.findAll((Specification<Book>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (names != null && !names.isEmpty()) {
                predicates.add(root.get("bookId").in(names));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
                    