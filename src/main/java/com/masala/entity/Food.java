package com.masala.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;
//    @ManyToOne
//    private Catagory foodCatagory;
//    @Column(length = 1000)
//    @ElementCollection
//    List<String> images = new ArrayList<>();
    private Boolean available;
//    @ManyToOne
//    private Restaurants restaurants;
    private Boolean isVegeterian;
    private Boolean isSeasonable;
//    @ManyToMany
//    private List<Ingredients> ingredients = new ArrayList<>();
//    private Date createdAt;
 }
