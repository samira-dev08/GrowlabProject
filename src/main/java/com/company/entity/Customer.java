package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    Integer id;
    String name;
    String address;
    String phone;
    LocalDate createdAt;
    LocalDate updatedAt;
}
