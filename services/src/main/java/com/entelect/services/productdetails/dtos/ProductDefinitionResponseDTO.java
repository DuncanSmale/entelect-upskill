package com.entelect.services.productdetails.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDefinitionResponseDTO {
    private String productCode = null;
    private Integer companyId = null;
    private String brandName = null;
    private String segment = null;
    private List<String> changeProduct = null;
    private List<ProductDefinitionPricingOption> pricingOption = null;
    private List<ProductDefinitionFeeCycleDate> feeCycleDate = null;
    private List<StatementCycleDates> statementCycleDates = null;
    private List<StatementType> statementType = null;
    private List<StatementDeliveryMethod> statementDeliveryMethod = null;
    private ProductPagePartner productPage = null;
    private ProductPagePartner moreValue = null;
    private String pricingGuideUrl = null;
    private String termsAndConditionUrl = null;
}
