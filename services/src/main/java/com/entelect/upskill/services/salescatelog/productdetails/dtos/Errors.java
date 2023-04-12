package com.entelect.upskill.services.salescatelog.productdetails.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Errors {
    private String code;
    private String source;
    private String details;
}
