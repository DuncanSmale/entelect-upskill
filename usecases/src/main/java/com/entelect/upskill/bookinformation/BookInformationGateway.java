package com.entelect.upskill.bookinformation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface BookInformationGateway {

    Response getBookInformation();

    @Setter
    @Getter
    class Response {
        List<String> bookInformationList;
    }


}
