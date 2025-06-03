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
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {

    private final MesaRepository      mesaRepo;
    private final MenuMesaRepository  menuRepo;

    @GetMapping("/{mesaId}/ticket")
    public ResponseEntity<byte[]> generarTicket(@PathVariable Integer mesaId) throws Exception {

        var mesa = mesaRepo.findById(mesaId).orElseThrow();
        var items = menuRepo.findByMesaId(mesaId);

        /* ========== construir PDF ========== */
        var baos = new ByteArrayOutputStream();
        var doc  = new Document(PageSize.A6, 20, 20, 25, 25);   // ticket pequeño
        PdfWriter.getInstance(doc, baos);
        doc.open();

        /* Encabezado */
        var h1 = new Paragraph("Restaurante XYZ", new Font(Font.HELVETICA, 14, Font.BOLD));
        h1.setAlignment(Element.ALIGN_CENTER);
        doc.add(h1);
        doc.add(new Paragraph("Ticket Mesa #" + mesa.getNumero()));
        doc.add(new Paragraph("Fecha: " +
              java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        doc.add(new Paragraph("Mesero: " + (mesa.getMesero() != null ? mesa.getMesero().getNombre() : "—")));
        doc.add(Chunk.NEWLINE);

        /* Tabla de productos */
        var table = new PdfPTable(new float[]{ 3, 1 });
        table.setWidthPercentage(100);
        table.addCell("Producto"); table.addCell("Precio");

        double total = 0;
        for (var it : items) {
            table.addCell(it.getProducto().getNombre());
            table.addCell(String.format("$ %.2f", it.getProducto().getPrecio()));
            total += it.getProducto().getPrecio().doubleValue();
        }
        table.addCell("TOTAL");
        table.addCell(String.format("$ %.2f", total));

        doc.add(table);
        doc.close();

        /* ========== respuesta HTTP ========== */
        var bytes = baos.toByteArray();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=ticket_mesa_" + mesa.getNumero() + ".pdf")
                .body(bytes);
    }
}
