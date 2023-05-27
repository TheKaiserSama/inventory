package com.inventory.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandListResponseDTO implements Serializable {

    private List<BrandResponseDTO> brands;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}
