package com.practicas.opensource.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {
    public static String generarNumeroFactura() {
        Date dates = new Date();
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormater.format(dates);

        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String randomNumberFormatted = String.format("%05d", randomNumber);
        String invoiceNumber = "fact-" + formattedDate + "-" + randomNumberFormatted;
        return invoiceNumber;
    }
}
