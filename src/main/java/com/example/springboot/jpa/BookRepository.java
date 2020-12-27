package com.example.springboot.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Ahielg
 * @date 22/12/2020
 */
@Repository

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>, PagingAndSortingRepository<Book, Long>, BookRepositoryCustom {

    @Query("select o from Book o where o.bookId in :ids")
    List<Book> findByIds(@Param("ids") List<Long> ids);

    List<Book> queryTitle(@Param("title") String Title);

    List<Book> findByAuthorFirstName(String firstName);

    List<Book> findByAuthor_Country(String country);

    Book findByTitle(String title);

    List<Book> findByTitleLike(String title);

    List<Book> findByTitleNot(String title);

    List<Book> findByTitleContaining(String title);

    List<Book> findByTitleContainingOrTitleContaining(String title, String title2);

    List<Book> findByTitleContainingAndPageCountIsGreaterThan(String title, int pageCount);

    List<Book> findByTitleStartingWith(String title);

    List<Book> findByTitleIgnoreCase(String title);

    //PageCount
    List<Book> findByPageCountEquals(int pageCount);

    List<Book> findByPageCountGreaterThan(int pageCount);

    List<Book> findByPageCountLessThan(int pageCount);

    List<Book> findByPageCountLessThanEqual(int pageCount);

    List<Book> findByPageCountBetween(int min, int max);


    //Date
    List<Book> findByPublishDateBefore(Date date);

    //ORDER BY
    List<Book> findByPublishDateAfterOrderByTitleAsc(Date date);


    //LIMIT
    List<Book> findTop3ByOrderByPageCountDesc();

    List<Book> findFirstByOrderByPageCountDesc();

    List<Book> findFirstByTitleOrderByPageCountDesc(String title);

    List<Book> findByPageCountGreaterThan(int pageCount, Pageable page);

    Page<Book> findByPageCountLessThanEqual(int pageCount, Pageable page);

    //Slice<Book> findByPageCountLessThanEqual(int pageCount, Pageable page);

    List<Book> findByPageCountGreaterThan(int pageCount, Sort page);

}