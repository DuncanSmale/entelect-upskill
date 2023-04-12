package com.entelect.upskill.services.salescatelog.productrules.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRulesRequestDto {
    private String productCode;
    private Integer companyId;
    private String brandName;
    private String segment;
}


