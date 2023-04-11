package com.entelect.upskill.repository;

import com.entelect.upskill.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Long countBookEntitiesByAuthorId(@Param("authorId") Integer authorId);
    List<BookEntity> findBookEntitiesByAuthorId(@Param("authorId") Integer authorId);
}
