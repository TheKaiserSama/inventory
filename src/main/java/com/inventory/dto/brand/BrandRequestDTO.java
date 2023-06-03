package com.inventory.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandRequestDTO implements Serializable {

    @NonNull
    private String name;
    private String summary;
    private String content;

}
