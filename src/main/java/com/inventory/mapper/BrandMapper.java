package com.inventory.mapper;

import com.inventory.dto.brand.BrandRequestDTO;
import com.inventory.dto.brand.BrandResponseDTO;
import com.inventory.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

}
