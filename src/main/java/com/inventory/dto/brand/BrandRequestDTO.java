package com.inventory.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandRequestDTO implements Serializable {

    private String name;
    private String summary;
    private String content;

}
