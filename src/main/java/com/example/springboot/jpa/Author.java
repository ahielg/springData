package com.example.springboot.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Ahielg
 * @date 23/12/2020
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    private String firstName;

    private String lastName;

    private String country;

}
