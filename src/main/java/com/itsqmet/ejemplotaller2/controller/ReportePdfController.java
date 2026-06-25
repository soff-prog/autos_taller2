package com.itsqmet.ejemplotaller2.controller;

import com.itsqmet.ejemplotaller2.model.Auto;
import com.itsqmet.ejemplotaller2.service.AutoService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reporte")
@CrossOrigin("*")
public class ReportePdfController {

    @Autowired
    private AutoService autoService;

    @GetMapping("/pdf")
    public void exportarAPDF(HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=reporte_autos.pdf"
        );

        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        documento.add(new Paragraph("REPORTE GENERAL DE AUTOS"));
        documento.add(new Paragraph("SISTEMA DE GESTION DE VEHICULOS"));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(9);

        tabla.addCell("ID");
        tabla.addCell("Marca");
        tabla.addCell("Modelo");
        tabla.addCell("Año");
        tabla.addCell("Color");
        tabla.addCell("Precio");
        tabla.addCell("Numero Chasis");
        tabla.addCell("Estado");
        tabla.addCell("Cliente");

        List<Auto> autos = autoService.obtenerTodo();

        for (Auto auto : autos) {

            tabla.addCell(
                    auto.getId() != null
                            ? auto.getId().toString()
                            : "N/A"
            );

            tabla.addCell(
                    auto.getMarca() != null
                            ? auto.getMarca()
                            : "Sin marca"
            );

            tabla.addCell(
                    auto.getModelo() != null
                            ? auto.getModelo()
                            : "Sin modelo"
            );

            tabla.addCell(
                    auto.getAnio() != null
                            ? auto.getAnio().toString()
                            : "N/A"
            );

            tabla.addCell(
                    auto.getColor() != null
                            ? auto.getColor()
                            : "Sin color"
            );

            tabla.addCell(
                    auto.getPrecio() != null
                            ? auto.getPrecio().toString()
                            : "0.0"
            );

            tabla.addCell(
                    auto.getNumeroChasis() != null
                            ? auto.getNumeroChasis()
                            : "Sin chasis"
            );

            tabla.addCell(
                    auto.getEstado() != null
                            ? auto.getEstado()
                            : "Sin estado"
            );

            if (auto.getCliente() != null) {
                tabla.addCell(
                        auto.getCliente().getNombre()
                                + " "
                                + auto.getCliente().getApellido()
                );
            } else {
                tabla.addCell("Sin cliente");
            }
        }

        documento.add(tabla);

        documento.close();
    }
}