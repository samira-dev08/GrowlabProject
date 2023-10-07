package com.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReq {
    @Size(max=15, message = "Name can be up to 15 characters")
    String name;
    String address;
    @NotBlank(message = "Phone can not be blank")
    String phone;
}
