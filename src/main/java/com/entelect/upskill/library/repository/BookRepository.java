package com.entelect.upskill.library.repository;

import com.entelect.upskill.library.aggregation.BookCount;
import com.entelect.upskill.library.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query("SELECT new com.entelect.upskill.library.aggregation.BookCount(b.author,COUNT(b.author))FROM BookEntity AS b GROUP BY b.author")
    public List<BookCount> getBookCountByAuthor();

    @Query("SELECT new com.entelect.upskill.library.aggregation.BookCount(b.author,COUNT(b.author)) FROM BookEntity AS b WHERE b.author.authorId = :authorId GROUP BY b.author ")
    public BookCount getBookCountBySingleAuthor(@Param("authorId") Integer authorId);
}
