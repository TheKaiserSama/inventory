package com.inventory.mapper;

import com.inventory.dto.brand.BrandListResponseDTO;
import com.inventory.dto.brand.BrandRequestDTO;
import com.inventory.dto.brand.BrandResponseDTO;
import com.inventory.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    public BrandResponseDTO toBrandResponseDTO(Brand brand) {
        return BrandResponseDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .summary(brand.getSummary())
                .createdAt(brand.getCreatedAt().toString())
                .updatedAt(brand.getUpdatedAt().toString())
                .content(brand.getContent())
                .build();
    }

    public Brand toBrand(BrandResponseDTO brandResponseDTO) {
        return Brand.builder()
                .id(brandResponseDTO.getId())
                .name(brandResponseDTO.getName())
                .summary(brandResponseDTO.getSummary())
                .createdAt(LocalDateTime.parse(brandResponseDTO.getCreatedAt()))
                .updatedAt(LocalDateTime.parse(brandResponseDTO.getUpdatedAt()))
                .content(brandResponseDTO.getContent())
                .build();
    }

    public Brand toBrand(BrandRequestDTO brandRequestDTO) {
        return Brand.builder()
                .name(brandRequestDTO.getName())
                .summary(brandRequestDTO.getSummary())
                .content(brandRequestDTO.getContent())
                .build();
    }

    public BrandListResponseDTO toBrandListResponseDTO(Page<Brand> brandPage) {
        List<BrandResponseDTO> brandResponseDTOList = brandPage.getContent()
                .stream()
                .map(this::toBrandResponseDTO)
                .toList();

        return BrandListResponseDTO.builder()
                .brands(brandResponseDTOList)
                .currentPage(brandPage.getNumber())
                .totalItems(brandPage.getTotalElements())
                .totalPages(brandPage.getTotalPages())
                .build();
    }

}
