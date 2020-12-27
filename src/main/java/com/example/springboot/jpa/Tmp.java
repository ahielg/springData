package com.example.springboot.jpa;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        // insertBook();
        findAll();
        findByTitle();
        findDate();
        findByAuthor();
        queryTitle();

        paging();

        sort();
    }

    private void paging() {
        System.out.println(repo.findAll(PageRequest.of(0, 3)));
        System.out.println(repo.findByPageCountGreaterThan(20, PageRequest.of(1, 2)));
        Page<Book> pages = repo.findByPageCountLessThanEqual(20, PageRequest.of(1, 2));

        System.out.println(pages.getTotalElements() + " ::: " + pages.getTotalPages());
        for (Book book : pages) {
            System.out.println(book);
        }
    }

    private void sort() {
        System.out.println(repo.findAll(Sort.by(Sort.Direction.ASC, "author.lastName", "pageCount")));
        System.out.println(repo.findByPageCountGreaterThan(20, Sort.by(Sort.Direction.ASC, "author.lastName", "pageCount")));
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
