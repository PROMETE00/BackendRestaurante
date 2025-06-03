package com.restaurante.api.controller;

import com.restaurante.api.dto.MenuMesaDTO;
import com.restaurante.api.model.MenuMesa;
import com.restaurante.api.model.Mesa;
import com.restaurante.api.model.Producto;
import com.restaurante.api.repository.MenuMesaRepository;
import com.restaurante.api.repository.MesaRepository;
import com.restaurante.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MenuMesaController {

    private final MenuMesaRepository repo;
    private final MesaRepository     mesaRepo;
    private final ProductoRepository prodRepo;

    /* ---------- GET /api/mesas/{mesaId}/productos ---------- */
    @GetMapping("/{mesaId}/productos")
    public List<MenuMesaDTO> productosDeMesa(@PathVariable Integer mesaId) {
        return repo.findByMesaId(mesaId)
                   .stream()
                   .map(this::toDTO)
                   .toList();
    }

    /* ---------- POST /api/mesas/{mesaId}/productos ---------- */
    @PostMapping("/{mesaId}/productos")
    public MenuMesaDTO agregar(@PathVariable Integer mesaId,
                               @RequestBody Map<String, Integer> body) {

        Integer productoId = body.get("productoId");

        Mesa     mesa     = mesaRepo .getReferenceById(mesaId);
        Producto producto = prodRepo .getReferenceById(productoId);

        MenuMesa mm = new MenuMesa();
        mm.setMesa(mesa);
        mm.setProducto(producto);

        mm = repo.save(mm);
        return toDTO(mm);
    }

    /* ---------- DELETE /api/mesas/{mesaId}/productos/{mmId} ---------- */
    @DeleteMapping("/{mesaId}/productos/{mmId}")
    public void quitar(@PathVariable Integer mmId) {
        repo.deleteById(mmId);
    }

    /* ---------- mapper privado ---------- */
    private MenuMesaDTO toDTO(MenuMesa mm) {
        return new MenuMesaDTO(
                mm.getId(),
                mm.getMesa().getId(),
                mm.getProducto().getId(),
                mm.getProducto().getNombre(),
                mm.getProducto().getPrecio()
        );
    }
}
