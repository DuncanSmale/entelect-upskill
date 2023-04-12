package com.entelect.upskill.services.salescatelog.productrules.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QualifyingAccounts {

    private String productCode = null;


    private String subProductCode = null;


    private Integer accountCompanyCode = null;


    private List<String> accountStatusCodes = null;


    private List<String> relationshipCodes = null;


    private String currencyCode = null;
}
