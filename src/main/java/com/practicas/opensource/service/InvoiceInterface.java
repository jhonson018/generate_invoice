package com.practicas.opensource.service;

import com.practicas.opensource.dto.InvoiceDTO;
import com.practicas.opensource.model.Invoice;

public interface InvoiceInterface {
  String createInvoice(InvoiceDTO invoice);
}
