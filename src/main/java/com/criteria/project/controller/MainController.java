package com.criteria.project.controller;

import com.criteria.project.dto.PageInfoDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class MainController {

    protected Pageable setDefaultPageableParameters(PageInfoDTO pageInfoDTO) {

        if(Objects.nonNull(pageInfoDTO) && Objects.isNull(pageInfoDTO.getPage())) {
            pageInfoDTO.setPage(0);
        }
        if(Objects.nonNull(pageInfoDTO) && Objects.isNull(pageInfoDTO.getSize())) {
            pageInfoDTO.setSize(50);
        }

        if(Objects.isNull(pageInfoDTO)){
            pageInfoDTO.setPage(0);
            pageInfoDTO.setSize(50);
        }

        Pageable pageable = PageRequest.of(pageInfoDTO.getPage(),pageInfoDTO.getSize());
        return pageable;
    }
}
