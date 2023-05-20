package com.inventory.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String summary;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
    private String content;

}
