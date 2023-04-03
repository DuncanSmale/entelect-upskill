package com.entelect.upskill.library.repository;

import com.entelect.upskill.library.common.AbstractRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql({
        "/data.sql"
})
@EntityScan("com.entelect.upskill.library.model")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository cut;

    private final static Integer AUTHOR_ONE_BOOK = 3;
    private final static Integer AUTHOR_FOUR_BOOKS = 1;
    private final static Integer AUTHOR_NO_BOOKS = 4;

    @Test
    @DisplayName("Given an author with one book, " +
            "when the countBookEntitiesByAuthorId method is invoked, " +
            "then a count of one is returned")
    void countBookByAuthorIdSingleBook() {
        Long testBookCountForAuthor = cut.countBookEntitiesByAuthorId(AUTHOR_ONE_BOOK);

        assertEquals(1L, testBookCountForAuthor);
    }

    @Test
    @DisplayName("Given an author with no books, " +
            "when the countBookEntitiesByAuthorId method is invoked, " +
            "then a count of zero is returned")
    void countBookByAuthorIdNoBooks() {
        Long testBookCountForAuthor = cut.countBookEntitiesByAuthorId(AUTHOR_NO_BOOKS);

        assertEquals(0L, testBookCountForAuthor);
    }

    @Test
    @DisplayName("Given an author with four books, " +
            "when the countBookEntitiesByAuthorId method is invoked, " +
            "then a count of four is returned")
    void countBookByAuthorIdFourBooks() {
        Long testBookCountForAuthor = cut.countBookEntitiesByAuthorId(AUTHOR_FOUR_BOOKS);

        assertEquals(4L, testBookCountForAuthor);
    }
}