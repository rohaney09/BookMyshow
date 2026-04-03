package com.example.BookMyShow.payment.service;

import com.example.BookMyShow.Booking.model.Booking;
import com.example.BookMyShow.generic.QrCodeUtil;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.modelmapper.internal.bytebuddy.implementation.Implementation;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

public class TicketPdfGenerator {
    public static byte[] generateTicketPdf(Booking booking) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Title
        document.add(new Paragraph("Movie Ticket")
                .setFontSize(18)
                .setBold());

        document.add(new Paragraph("Booking ID: " + booking.getBookingCode()));
        document.add(new Paragraph("Movie ID: " + booking.getShow().getMovieId()));
        document.add(new Paragraph("Theatre ID: " + booking.getShow().getTheatreId()));
        document.add(new Paragraph("Show Time: " + booking.getShow().getShowTime()));
        document.add(new Paragraph("Seats: " + booking.getSeatsBooked()));
        document.add(new Paragraph("Total Amount: ₹" + booking.getTotalAmount()));

        document.add(new Paragraph("\nScan QR at theatre entry"));

        // QR CODE
        String qrText =
                booking.getBookingCode() + "|" +
                        booking.getShow().getShowId() + "|" +
                        booking.getUser().getUserId();

        byte[] qrImage = QrCodeUtil.generateQrCode(qrText);

        Image qr = new Image(ImageDataFactory.create(qrImage));
        qr.setWidth(150);
        qr.setHeight(150);

        document.add(qr);

        document.close();
        return out.toByteArray();
    }
}
