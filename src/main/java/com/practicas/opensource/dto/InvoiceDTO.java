package com.practicas.opensource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practicas.opensource.model.InvoiceConditions;
import com.practicas.opensource.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InvoiceDTO {
    private InvoiceConditions invoiceConditions;
    private UserDTO user;
    private String invoiceDate;
    private String invoiceAmount;
    private Boolean status;
}
