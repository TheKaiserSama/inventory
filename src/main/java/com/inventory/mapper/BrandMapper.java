package com.inventory.mapper;

import com.inventory.dto.brand.BrandResponseDTO;
import com.inventory.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final ConversionService conversionService;

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
                .createdAt(conversionService.convert(brandResponseDTO.getCreatedAt(), Date.class))
                .updatedAt(conversionService.convert(brandResponseDTO.getUpdatedAt(), Date.class))
                .content(brandResponseDTO.getContent())
                .build();
    }

}
