package com.entelect.services.productdetails;

import com.entelect.services.common.RestTemplateCaller;
import com.entelect.services.productdetails.dtos.ProductDefinitionRequestDTO;
import com.entelect.services.productdetails.dtos.ProductDefinitionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class ProductDefinitionGatewayImpl {
    private final RestTemplateCaller<ProductDefinitionRequestDTO, ProductDefinitionResponseDTO> restTemplate;

    public ProductDefinitionResponseDTO getProductDefinition() {
        ProductDefinitionRequestDTO request = new ProductDefinitionRequestDTO(
                "DDAAA",
                1,
                "",
                "");

        String url = "http://localhost:9091/sales-catalogue/product-definition";
        String uri = UriComponentsBuilder.fromHttpUrl(url).toUriString();

        ProductDefinitionResponseDTO productResponse = restTemplate.postForEntity(
                URI.create(uri),
                request,
                ProductDefinitionResponseDTO.class).getBody();
        return productResponse;
    }
}
