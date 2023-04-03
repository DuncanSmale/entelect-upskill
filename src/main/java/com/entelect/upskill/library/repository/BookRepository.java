package com.entelect.upskill.library.repository;

import com.entelect.upskill.library.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Long countBookEntitiesByAuthorId(@Param("authorId") Integer authorId);
}
