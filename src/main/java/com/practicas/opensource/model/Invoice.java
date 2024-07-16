package com.practicas.opensource.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Invoice {
    private String invoiceNumber;
    private InvoiceConditions invoiceConditions;
    private User user;
    private String invoiceDate;
    private String invoiceAmount;
    private Boolean status;
}
