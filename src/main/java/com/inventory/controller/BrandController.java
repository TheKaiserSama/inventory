package com.inventory.controller;

import com.inventory.dto.brand.BrandResponseDTO;
import com.inventory.model.Brand;
import com.inventory.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands() {
        List<BrandResponseDTO> brandResponseDTOList = brandService.getAllBrands();
        return new ResponseEntity<>(brandResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable("brandId") Long brandId) {
        BrandResponseDTO brandResponseDTO = brandService.getBrandById(brandId);
        return new ResponseEntity<>(brandResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand brandSaved = brandService.createBrand(brand);
        return new ResponseEntity<>(brandSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> updateBrandById(
            @RequestBody Brand brand, @PathVariable("brandId") Long brandId) {
        BrandResponseDTO brandUpdated = brandService.updateBrandById(brand, brandId);
        return new ResponseEntity<>(brandUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<String> deleteBrandById(@PathVariable("brandId") Long brandId) {
        brandService.deleteBrandById(brandId);
        return ResponseEntity.ok("Brand removed successfully");
    }

}
