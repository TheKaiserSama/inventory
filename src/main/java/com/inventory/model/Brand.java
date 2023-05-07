package com.inventory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    @NotBlank(message = "Summary should not blank")
    private String summary;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

}
