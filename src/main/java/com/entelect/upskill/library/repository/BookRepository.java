package com.entelect.upskill.library.repository;

import com.entelect.upskill.library.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
//    public List<Long> countBy();

    Long countBookEntitiesByAuthorId(@Param("authorId") Integer authorId);
}
