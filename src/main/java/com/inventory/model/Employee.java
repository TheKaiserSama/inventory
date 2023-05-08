package com.inventory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    private String title;
    private String titleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phoneHome;
    private String extension;
    private String photoUrl;
    private String notes;

    @PositiveOrZero(message = "The employee's salary cannot be negative")
    private double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "reports_to")
    private Employee parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Employee> children = new HashSet<>();

}
