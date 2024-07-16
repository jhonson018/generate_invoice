package com.practicas.opensource.service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.practicas.opensource.InvoiceException;
import com.practicas.opensource.dto.InvoiceDTO;
import com.practicas.opensource.dto.UserDTO;
import com.practicas.opensource.model.Invoice;
import com.practicas.opensource.model.InvoiceConditions;
import com.practicas.opensource.model.User;
import com.practicas.opensource.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.LocalDateTime;

@Service
@Log4j2
public class InvoiceService implements InvoiceInterface{

    @Override
    public String createInvoice(InvoiceDTO invoice) {
        log.info("New invoice received: {}", invoice.toString());
        if (invoice == null) {
            throw new InvoiceException("AN ERROR OCCURRED WHEN CREATING THE INVOICE", HttpStatus.BAD_REQUEST);
        } else {
            Invoice invoiceFac = createInvoiceObject(invoice);
            try {
                // Usar el directorio temporal del sistema
                String tempDir = System.getProperty("java.io.tmpdir");
                String dest = tempDir + "/invoice_" + invoiceFac.getUser().getIdentification() + "-" + invoiceFac.getInvoiceNumber() + ".pdf";

                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf, PageSize.A4);

                // Ajustar márgenes
                document.setMargins(20, 20, 20, 20);

                // Fuente personalizada
                PdfFont font = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);
                PdfFont bold = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);

                // Título del documento
                Paragraph title = new Paragraph("Invoice")
                        .setFont(bold)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(20);
                document.add(title);

                // Contenido del documento
                document.add(new Paragraph("Invoice ID: " + invoiceFac.getInvoiceNumber())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));
                document.add(new Paragraph("Customer Name: " + invoiceFac.getUser().getName() + " " + invoiceFac.getUser().getLastName())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));
                document.add(new Paragraph("User identification: " + invoiceFac.getUser().getIdentification())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));

                document.add(new Paragraph("Invoice Date: " + invoiceFac.getInvoiceDate())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));
                document.add(new Paragraph("Invoice amount: " + invoiceFac.getInvoiceAmount())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));
                document.add(new Paragraph("Invoice Conditions: " + invoiceFac.getInvoiceConditions())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(10));

                document.close();
                return dest;
            } catch (Exception e) {
                e.printStackTrace(); // Imprime el stack trace completo
                throw new InvoiceException(e.getMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private Invoice createInvoiceObject(InvoiceDTO invoice){
        User user = createUserObject(invoice.getUser());
        return Invoice.builder()
                .invoiceNumber(Utils.generarNumeroFactura())
                .invoiceConditions(invoice.getInvoiceConditions())
                .user(user)
                .invoiceDate(LocalDateTime.now().toString())
                .invoiceAmount(invoice.getInvoiceAmount())
                .status(true)
                .build();
    }
    private User createUserObject(UserDTO user){
        return User.builder()
                .identification(user.getIdentification())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }
}
