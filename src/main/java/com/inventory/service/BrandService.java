package com.inventory.service;

import com.inventory.dto.brand.BrandRequestDTO;
import com.inventory.dto.brand.BrandResponseDTO;
import com.inventory.exception.ResourceExistsException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.BrandMapper;
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
    private final BrandMapper brandMapper;

    public BrandResponseDTO getBrandById(Long brandId) {
        Optional<BrandResponseDTO> brand = brandRepository.findById(brandId).map(brandMapper::toBrandResponseDTO);
        return brand.orElseThrow(() -> new ResourceNotFoundException("Brand with id=[" + brandId + "] not found"));
    }

    public List<BrandResponseDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toBrandResponseDTO)
                .toList();
    }

    public BrandResponseDTO createBrand(BrandRequestDTO brandRequestDTO) {
        Optional<Brand> brandFound = brandRepository.findBrandByName(brandRequestDTO.getName());
        if (brandFound.isEmpty()) {
            Brand brandSaved = brandRepository.save(brandMapper.toBrand(brandRequestDTO));
            return brandMapper.toBrandResponseDTO(brandSaved);
        }
        throw new ResourceExistsException("Brand with name=[" + brandRequestDTO.getName() + "] already exists");
    }

    public BrandResponseDTO updateBrandById(BrandRequestDTO brand, Long brandId) {
        BrandResponseDTO brandFound = this.getBrandById(brandId);
        brandFound = brandFound.toBuilder()
                .name(brand.getName())
                .summary(brand.getSummary())
                .content(brand.getContent())
                .build();
        Brand brandUpdated = brandRepository.save(brandMapper.toBrand(brandFound));
        return brandMapper.toBrandResponseDTO(brandUpdated);
    }

    public void deleteBrandById(Long brandId) {
        this.getBrandById(brandId);
        brandRepository.deleteById(brandId);
    }

}
