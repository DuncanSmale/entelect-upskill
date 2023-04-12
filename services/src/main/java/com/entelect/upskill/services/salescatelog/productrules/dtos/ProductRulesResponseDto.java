package com.entelect.upskill.services.salescatelog.productrules.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductRulesResponseDto {

    private String productKey = null;


    private Integer companyId = null;


    private String brandName = null;


    private String segment = null;


    private List<String> customerTypes = null;


    private List<String> sicCodes = null;


    private Boolean resident = null;


    private Boolean singleMember = null;


    private List<String> religions = null;


    private NumberOfEmployees numberOfEmployees = null;

    private MonthsOfEstablishment monthsOfEstablishment = null;


    private List<QualifyingAccounts> qualifyingAccounts = null;


    private List<String> getkYCStatus = null;


    private List<String> turnoverCodes = null;


    private Boolean franchise = null;


    private Boolean getvIPVIB = null;


    private List<AlternateProducts> alternateProducts = null;


    private List<CrossSell> getxSell = null;


    private List<MandatoryXsell> mandatoryXsell = null;
}
