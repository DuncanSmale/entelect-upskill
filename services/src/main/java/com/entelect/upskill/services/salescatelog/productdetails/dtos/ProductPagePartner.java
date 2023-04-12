package com.entelect.upskill.services.salescatelog.productdetails.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPagePartner {
    private String resourceUrl = null;

    private ShopImage bgImage = null;

    private ShopImage fgImage = null;

    private String fgTextColour = null;

    private String pageContent = null;
}
