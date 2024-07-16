package com.practicas.opensource.model;

public enum InvoiceConditions {

    PAID("Factura pagada"),
    PENDING("Factura pendiente de pago"),
    CANCELLED("Factura cancelada");

    private String condition;

    InvoiceConditions(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
