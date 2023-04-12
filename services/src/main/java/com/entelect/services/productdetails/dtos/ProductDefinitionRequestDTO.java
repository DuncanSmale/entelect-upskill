package com.entelect.services.productdetails.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDefinitionRequestDTO {
    private String productCode;
    private Integer companyId;
    private String brandName;
    private String segment;
}
