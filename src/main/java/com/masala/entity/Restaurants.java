package com.masala.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User owner;
    private String description;
    private String cuisineType;
    @OneToOne
    private Address address;
    @Embedded
    private ContactInformation contactInformation;
    private String openingHours;
    @OneToMany(mappedBy = "restaurants", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>(); // here the problem arises by cascading with order, if user deleted shud be delete for resturant also
    @Column(length = 1000)
    private List<String> images;
    private LocalDateTime registrationDate;
    private Boolean isOpen;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurants",cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();
}
