package com.company.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    UUID uniqueExceptionId;
    LocalDate timeStamp;
    String message;
    String details;
}
