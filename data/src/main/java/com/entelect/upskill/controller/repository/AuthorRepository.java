package com.entelect.upskill.controller.repository;


import com.entelect.upskill.controller.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
