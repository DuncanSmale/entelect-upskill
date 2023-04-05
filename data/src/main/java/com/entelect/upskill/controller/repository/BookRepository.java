package com.entelect.upskill.controller.repository;

import com.entelect.upskill.controller.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
