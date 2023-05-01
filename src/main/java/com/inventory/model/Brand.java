package com.inventory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")

public class Brand {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private int id;
    private String name;
    private String summary;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

}
