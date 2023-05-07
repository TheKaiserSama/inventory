package com.inventory.controller;

import com.inventory.model.Brand;
import com.inventory.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brandList = brandService.getAllBrands();
        return new ResponseEntity<>(brandList, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("brandId") Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand brandSaved = brandService.createBrand(brand);
        return new ResponseEntity<>(brandSaved, HttpStatus.CREATED);
    }

}
