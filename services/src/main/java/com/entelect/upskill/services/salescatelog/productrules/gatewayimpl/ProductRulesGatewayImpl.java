package com.entelect.upskill.services.salescatelog.productrules.gatewayimpl;

import com.entelect.upskill.services.common.RestTemplateCaller;
import com.entelect.upskill.services.salescatelog.productrules.dtos.ProductRulesRequestDto;
import com.entelect.upskill.services.salescatelog.productrules.dtos.ProductRulesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ProductRulesGatewayImpl {
    private final RestTemplateCaller<ProductRulesRequestDto, ProductRulesResponseDto> restTemplate;

    public ProductRulesResponseDto getProductRules() {
        ProductRulesRequestDto request = new ProductRulesRequestDto(
                "DDAAA",
                1,
                "",
                "");

        String url = "http://localhost:9091/sales-catalogue/product-rules";
        String uri = UriComponentsBuilder.fromHttpUrl(url).toUriString();

        ProductRulesResponseDto productRulesResponse = restTemplate.postForEntity(
                URI.create(uri),
                request,
                ProductRulesResponseDto.class).getBody();
        return productRulesResponse;
    }
}

