package com.entelect.upskill.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE books SET deleted = 1 WHERE book_id=?")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    private String title;

    private LocalDate publishedDate;

    private String publisher;

    private String ISBN;

    private boolean deleted;
}
