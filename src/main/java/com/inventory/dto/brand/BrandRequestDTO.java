package com.inventory.dto.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class BrandRequestDTO {

    private String name;
    private String summary;
    private String content;

}
