package com.example.springboot.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ahielg
 * @date 22/12/2020
 */
@Entity
@Table(name = "BOOK")
@Getter
@Setter
@ToString
@NamedQueries(
        @NamedQuery(name = "Book.queryTitle", query = "select b from Book b where b.title = :title")
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private Date publishDate;

    private int pageCount;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

}
