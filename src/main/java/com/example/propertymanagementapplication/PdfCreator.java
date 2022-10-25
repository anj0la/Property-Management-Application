package com.example.propertymanagementapplication;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.colors.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.layout.properties.TextAlignment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PdfCreator {
    private Document document;
    private final float[] tableColWidths = {85F, 110F, 110F, 110F, 80F, 80F, 80F, 80F};
    private final float[] headerColWidths = {400F, 175F};
    private final float[] yearlyTableColWidths = {110F, 110F, 110F, 110F, 110F};

    /**
     * Initializes the document variable to be used to create a PDF document, by using a filename to create a PdfWriter
     * instance.
     * @param dest the file destination
     * @throws FileNotFoundException if not file was found
     */
    public PdfCreator(String dest) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        document = new Document(pdfDoc);
    } // Constructor

    /**
     * Initializes the document variable to be used to create a PDF document, by using a file to create a PdfWriter
     * instance.
     * @param file the file
     * @throws FileNotFoundException if not file was found
     */
    public PdfCreator(File file) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDoc = new PdfDocument(writer);
        document = new Document(pdfDoc);
    }

    /**
     * Constructs the monthly report.
     * @throws SQLException when no database connection can be established
     */
    public void createMonthlyReport() throws SQLException, IOException {
        Table header = new Table(headerColWidths);
        Table table = new Table(tableColWidths);
        createMonthlyReportHeader(header);
        SolidLine line = new SolidLine(2f);
        line.setColor(Color.convertRgbToCmyk(new DeviceRgb(255,255,255)));
        LineSeparator lineSeparator = new LineSeparator(line);
        lineSeparator.setMarginTop(10f);
        lineSeparator.setMarginBottom(10f);
        document.add(header);
        document.add(lineSeparator);
        createMonthlyTable(table);
        document.add(table);
        document.close();
    } // createMonthlyReportPDF

    /**
     * Creates the header for the monthly report PDF document.
     */
    private void createMonthlyReportHeader(Table header) throws IOException{
        PdfFont headerFont = PdfFontFactory.createFont(StandardFonts.COURIER_BOLDOBLIQUE);
        PdfFont headerFont2 = PdfFontFactory.createFont(StandardFonts.COURIER_OBLIQUE);
        Paragraph title = new Paragraph("Monthly Operations Report");
        header.addCell(new Cell().add(title).setFont(headerFont).setFontSize(22f).setBorder(Border.NO_BORDER));
        header.addCell(new Cell().add(new Paragraph("Company Name Goes Here").setFontSize(10f).
                setFont(headerFont2)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Date: " + LocalDate.now())).setBorder(Border.NO_BORDER).
                setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("Some Address").setFont(headerFont2).setFontSize(10f)).
                setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Name: _________________________")).setBorder(Border.NO_BORDER).
                setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("City, Province Postal Code").setFont(headerFont2)).
                setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Email: ________________________")).
                setBorder(Border.NO_BORDER).setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("XXX-XXX-XXXX")).setBorder(Border.NO_BORDER).
                setFont(headerFont2).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT));
    } // createMonthlyReportHeader

    /**
     * Creates the monthly report table header.
     * @param table the table to contain all the data from the regular table in the database.
     */
    private void createMonthlyReportTableHeader(Table table) {
        table.addCell(new Cell().add(new Paragraph("Date Joined").
                setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Client").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Tenant").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Address").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Rent").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Expenses").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Commission").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Client Payment").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
    } // createMonthlyReportHeader

    /**
     * Creates the table that will be present in the PDF report.
     * @param table the table to add all the database information
     * @throws SQLException when no database connection can be established
     */
    private void createMonthlyTable(Table table) throws SQLException {
        Connection connection = DatabaseConnector.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from regular_table");
        ResultSet resultSet = preparedStatement.executeQuery();
        createMonthlyReportTableHeader(table);
        while (resultSet.next()) {
            String dateJoined = resultSet.getString("date_joined");
            table.addCell(new Cell().add(new Paragraph(dateJoined)));
            String clientName = resultSet.getString("client_name");
            table.addCell(new Cell().add(new Paragraph(clientName)));
            String tenantName = resultSet.getString("tenant_name");
            table.addCell(new Cell().add(new Paragraph(tenantName)));
            String address = resultSet.getString("address");
            table.addCell(new Cell().add(new Paragraph(address)));
            BigDecimal rent = resultSet.getBigDecimal("rent");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(rent))));
            BigDecimal expenses = resultSet.getBigDecimal("expenses");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(expenses))));
            BigDecimal commission = resultSet.getBigDecimal("commission");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(commission))));
            BigDecimal clientPayment = resultSet.getBigDecimal("client_payment");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(clientPayment))));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } // createMonthlyTable

    /**
     * Constructs the yearly report.
     * @throws SQLException when no database connection can be established
     */
    public void createYearlyReport() throws SQLException, IOException {
        Table header = new Table(headerColWidths);
        Table yearlyTable = new Table(yearlyTableColWidths);
        createYearlyReportHeader(header);
        SolidLine line = new SolidLine(2f);
        line.setColor(Color.convertRgbToCmyk(new DeviceRgb(255,255,255)));
        LineSeparator lineSeparator = new LineSeparator(line);
        lineSeparator.setMarginTop(10f);
        lineSeparator.setMarginBottom(10f);
        document.add(header);
        document.add(lineSeparator);
        createYearlyTable(yearlyTable);
        document.add(yearlyTable);
        document.close();
    } // createYearlyReport

    /**
     * Creates the header for the yearly report PDF document.
     */
    private void createYearlyReportHeader(Table header) throws IOException {
        PdfFont headerFont = PdfFontFactory.createFont(StandardFonts.COURIER_BOLDOBLIQUE);
        PdfFont headerFont2 = PdfFontFactory.createFont(StandardFonts.COURIER_OBLIQUE);
        header.addCell(new Cell().add(new Paragraph("Yearly Operations Report")).
                setFont(headerFont).setFontSize(22f).setBorder(Border.NO_BORDER));
        header.addCell(new Cell().add(new Paragraph("Company Name Goes Here").setFontSize(10f).
                setFont(headerFont2)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Date: " + LocalDate.now())).setBorder(Border.NO_BORDER).
                setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("Some Address").setFont(headerFont2).setFontSize(10f)).
                setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Name: _________________________")).setBorder(Border.NO_BORDER).
                setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("City, Province Postal Code").setFont(headerFont2)).
                setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        header.addCell(new Cell().add(new Paragraph("Email: ________________________")).
                setBorder(Border.NO_BORDER).setFont(headerFont2));
        header.addCell(new Cell().add(new Paragraph("XXX-XXX-XXXX")).setBorder(Border.NO_BORDER).
                setFont(headerFont2).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT));
    } // createYearlyReportHeader

    /**
     * Creates the yearly report table header.
     * @param table the table to contain all the data from the regular table in the database.
     */
    private void createYearlyReportTableHeader(Table table) {
        table.addCell(new Cell().add(new Paragraph("Month").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Total Rent").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Total Expenses").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Total Commission").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
        table.addCell(new Cell().add(new Paragraph("Total Payment").
                        setFontColor(Color.convertRgbToCmyk(new DeviceRgb(255, 255, 255)))).setBold().
                setBorder(Border.NO_BORDER).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0))));
    } // createYearlyReportTableHeader

    /**
     * Creates the yearly table that will be present in the PDF report.
     * @param table the table to add all the database information
     * @throws SQLException when no database connection can be established
     */
    private void createYearlyTable(Table table) throws SQLException {
        Connection connection = DatabaseConnector.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from yearly_table");
        ResultSet resultSet = preparedStatement.executeQuery();
        createYearlyReportTableHeader(table);
        while (resultSet.next()) {
            String monthId = resultSet.getString("month_id");
            table.addCell(new Cell().add(new Paragraph(monthId)));
            BigDecimal monthlyRent = resultSet.getBigDecimal("monthly_rent");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(monthlyRent))));
            BigDecimal monthlyExpenses = resultSet.getBigDecimal("monthly_expenses");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(monthlyExpenses))));
            BigDecimal monthlyCommission = resultSet.getBigDecimal("monthly_commission");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(monthlyCommission))));
            BigDecimal monthlyClientPayment = resultSet.getBigDecimal("monthly_client_payment");
            table.addCell(new Cell().add(new Paragraph(String.valueOf(monthlyClientPayment))));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } // createYearlyTable

} // class
