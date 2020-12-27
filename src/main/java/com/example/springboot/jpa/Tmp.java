package com.example.springboot.jpa;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Ahielg
 * @date 22/12/2020
 */
@Configuration
public class Tmp {

    @Autowired
    BookService service;

    @Autowired
    BookRepository repo;

    @PostConstruct
    void init() {
        insertBook();
        findAll();
        findByTitle();
        findDate();
        findByAuthor();
        queryTitle();

        paging();
    }

    private void paging() {
        System.out.println(repo.findAll(PageRequest.of(0,3)));
        System.out.println(repo.findByPageCountGreaterThan(20,PageRequest.of(1,2)));
    }

    private void queryTitle() {
        System.out.println(repo.queryTitle("book1"));
    }

    private void findAll() {
        List<Book> one = repo.findAll();
        System.out.println("AHIEL *********");
        System.out.println(one);
    }

    private void insertBook() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setPageCount(22);
        book.setPublishDate(new Date());
        service.save(book);
        Book findMe = new Book();
        findMe.setBookId(2L);
    }

    private void findByTitle() {
        System.out.println(repo.findByTitle("book4"));
        System.out.println(repo.findByTitleLike("%book%"));
        System.out.println(repo.findByTitleContaining("book"));
    }

    private void findByAuthor() {
        System.out.println(repo.findByAuthor_Country("IL"));
        System.out.println(repo.findByAuthorFirstName("ahiel"));
    }

    @SneakyThrows
    public void findDate() {
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2012");
        System.out.println(repo.findByPublishDateAfterOrderByTitleAsc(startDate));
    }
}
