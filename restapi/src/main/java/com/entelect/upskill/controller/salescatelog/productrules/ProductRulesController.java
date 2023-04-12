package com.entelect.upskill.controller.salescatelog.productrules;

import com.entelect.upskill.services.salescatelog.productrules.dtos.ProductRulesResponseDto;
import com.entelect.upskill.services.salescatelog.productrules.gatewayimpl.ProductRulesGatewayImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRulesController {
    private final ProductRulesGatewayImpl productRulesGateway;

    @GetMapping("product-rules")
    public ProductRulesResponseDto getProductRules() {
        return productRulesGateway.getProductRules();
    }
}
