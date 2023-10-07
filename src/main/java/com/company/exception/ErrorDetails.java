package com.company.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    LocalDate timeStamp;
    String message;
    String details;
}
