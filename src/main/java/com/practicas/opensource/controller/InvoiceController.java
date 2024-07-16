package com.practicas.opensource.controller;
import com.practicas.opensource.InvoiceException;
import com.practicas.opensource.dto.InvoiceDTO;
import com.practicas.opensource.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoice/form")
    public String showForm(Model model) {
        model.addAttribute("invoice", new InvoiceDTO());
        return "invoiceForm"; // El nombre del template HTML
    }

    @PostMapping("/invoice/submit")
    public String submitForm(@ModelAttribute InvoiceDTO invoice, Model model) {
        try {
            String pdfPath = invoiceService.createInvoice(invoice);
            model.addAttribute("pdfPath", pdfPath);
            return "invoiceSuccess"; // El nombre del template HTML para mostrar el éxito
        } catch (InvoiceException e) {
            model.addAttribute("error", e.getMessage());
            return "invoiceForm"; // Volver al formulario si hay un error
        }
    }
}
