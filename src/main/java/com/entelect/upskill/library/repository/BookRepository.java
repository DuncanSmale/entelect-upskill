package com.entelect.upskill.library.repository;

import com.entelect.upskill.library.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
