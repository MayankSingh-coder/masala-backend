package com.masala.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masala.dto.RestaurantsDto;
import com.masala.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User customer;
    @JsonIgnore
    @ManyToOne
    private Restaurants restaurants;
    @ManyToOne
    private Address address;
    @OneToMany
    private List<Items> items;
    private Long totalAmount;
    private OrderStatus orderStatus;
    private Date createdAt;
    private Date updatedAt;
    private Long totalItem;
}
