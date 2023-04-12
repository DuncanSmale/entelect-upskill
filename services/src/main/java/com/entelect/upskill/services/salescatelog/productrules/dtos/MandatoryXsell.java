package com.entelect.upskill.services.salescatelog.productrules.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MandatoryXsell {
    private String productCode = null;


    private String subProductCode = null;


    private Integer accountCompanyCode = null;


    private List<String> relationshipCode = null;


    private String currencyCode = null;


    private List<String> accountStatusCode = null;
}
