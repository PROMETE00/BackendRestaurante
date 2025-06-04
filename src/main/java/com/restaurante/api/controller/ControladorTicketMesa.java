package com.restaurante.api.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.restaurante.api.repository.MenuMesaRepository;
import com.restaurante.api.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ControladorTicketMesa {

    private final MesaRepository mesaRepository;
    private final MenuMesaRepository menuMesaRepository;

    @GetMapping("/{idMesa}/ticket")
    public ResponseEntity<byte[]> generarTicketPDF(@PathVariable Integer idMesa) throws Exception {

        var mesaSeleccionada = mesaRepository.findById(idMesa).orElseThrow();
        var productosMesa = menuMesaRepository.findByMesaId(idMesa);

        ByteArrayOutputStream salidaPDF = new ByteArrayOutputStream();
        Document documento = new Document(PageSize.A6, 20, 20, 25, 25);  // Tamaño tipo ticket
        PdfWriter.getInstance(documento, salidaPDF);
        documento.open();

        // Encabezado del ticket
        Paragraph encabezado = new Paragraph("Restaurante XYZ", new Font(Font.HELVETICA, 14, Font.BOLD));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        documento.add(encabezado);
        documento.add(new Paragraph("Ticket Mesa #" + mesaSeleccionada.getNumero()));
        documento.add(new Paragraph("Fecha: " +
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        documento.add(new Paragraph("Mesero: " +
                (mesaSeleccionada.getMesero() != null ? mesaSeleccionada.getMesero().getNombre() : "—")));
        documento.add(Chunk.NEWLINE);

        // Tabla de productos
        PdfPTable tablaProductos = new PdfPTable(new float[]{3, 1});
        tablaProductos.setWidthPercentage(100);
        tablaProductos.addCell("Producto");
        tablaProductos.addCell("Precio");

        double sumaTotal = 0;
        for (var item : productosMesa) {
            tablaProductos.addCell(item.getProducto().getNombre());
            tablaProductos.addCell(String.format("$ %.2f", item.getProducto().getPrecio()));
            sumaTotal += item.getProducto().getPrecio().doubleValue();
        }

        tablaProductos.addCell("TOTAL");
        tablaProductos.addCell(String.format("$ %.2f", sumaTotal));

        documento.add(tablaProductos);
        documento.close();

        byte[] contenidoPDF = salidaPDF.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=ticket_mesa_" + mesaSeleccionada.getNumero() + ".pdf")
                .body(contenidoPDF);
    }
}
