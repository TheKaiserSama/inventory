package com.inventory.service;

import com.inventory.model.Brand;
import com.inventory.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand getBrandById(Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        return brand.orElse(null);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean deleteBrandById(Long brandId) {
        brandRepository.deleteById(brandId);
        return brandRepository.existsById(brandId);
    }

}
