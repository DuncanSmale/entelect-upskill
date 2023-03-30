package com.entelect.upskill.library.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE authors SET deleted = 1 WHERE author_id=?")
@Where(clause = "deleted=false")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private String emailAddress;
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    private List<BookEntity> books = new ArrayList<>();
}
