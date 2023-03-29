package com.entelect.upskill.library.aggregation;

import com.entelect.upskill.library.model.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookCount {
    private AuthorEntity author ;
    private Long      bookCount;

}
