package com.masala.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ContactInformation {

    private String email;
    private String mobile;
    private String instagram;
    private String twitter;
}
