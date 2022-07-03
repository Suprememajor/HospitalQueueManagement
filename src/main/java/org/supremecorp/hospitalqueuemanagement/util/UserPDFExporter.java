package org.supremecorp.hospitalqueuemanagement.util;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class UserPDFExporter {
    public void export(HttpServletResponse response, Appointment appointment) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Hospital name: " + appointment.getUnit().getHospital().getName(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Unit name: " + appointment.getUnit().getName(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Unique Identifier: " + appointment.getIdentifier(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Date: " + "Date selected", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Time: " + "Time selected", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Patient name: " + appointment.getPatientName(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph("Gender: " + appointment.getGender().toString(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.0f});
        table.setSpacingBefore(10);

        Image image = Image.getInstance("generate_barcode.png");
        image.setAlignment(Image.ALIGN_CENTER);
        image.setAlignment(Image.ALIGN_BOTTOM);
        document.add(image);

        document.close();
    }
}
