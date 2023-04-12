package com.entelect.services.productdetails.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Errors {
    private String code;
    private String source;
    private String details;
}
