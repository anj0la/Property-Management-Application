package com.example.propertymanagementapplication;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PDFCreator {
    private Document document;
    private final float[] pColWidths = {90F, 90F, 90F, 90F, 90F, 90F, 90F, 90F};

    /**
     * Initializes the document variable to be used to create a PDF.
     * @param dest the file destination
     * @throws FileNotFoundException if not file was found
     */
    public PDFCreator(String dest) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        document = new Document(pdfDoc);
    } // Constructor

    /**
     * Constructs the monthly report PDF.
     * @throws SQLException when no database connection can be established
     */
    public void createMonthlyReportPDF() throws SQLException {
        Paragraph header = new Paragraph("Monthly Report");
        document.add(header);
        Table table = new Table(pColWidths);
        createTable(table);
        document.add(table);
        document.close();
    } // createMonthlyReportPDF

    /**
     * TODO - Create method.
     * @param dest
     * @throws IOException
     */
    public void displayMonthlyReportPDF(String dest) throws IOException {
        PdfReader reader = new PdfReader(dest);
    }

    /**
     * Creates the table that will be present in the PDF report.
     * @param table the table to add all the database information
     * @throws SQLException when no database connection can be established
     */
    private void createTable(Table table) throws SQLException {
        Connection connection = DatabaseConnector.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from client.regular_table");
        ResultSet resultSet = preparedStatement.executeQuery();

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
    } // createTable

} // class
