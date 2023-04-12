package com.entelect.upskill.productdefinition;

import com.entelect.services.productdetails.ProductDefinitionGatewayImpl;
import com.entelect.services.productdetails.dtos.ProductDefinitionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductDefinitionController {

    private final ProductDefinitionGatewayImpl productDefinitionGateway;

    @GetMapping("product-definition")
    public ProductDefinitionResponseDTO getProductDefinition() {
        return productDefinitionGateway.getProductDefinition();
    }

}
