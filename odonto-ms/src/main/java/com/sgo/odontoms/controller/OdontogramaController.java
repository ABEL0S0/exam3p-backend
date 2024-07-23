package com.sgo.odontoms.controller;

import com.sgo.odontoms.entity.Odontograma;
import com.sgo.odontoms.repository.OdontogramaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontograma")
public class OdontogramaController {

    @Autowired
    private OdontogramaRepository repository;

    private static String subida_dir = "odontogramas/";

    @Operation(summary = "Obtener lista de odontogramas")
    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<Odontograma> getAllOdontogramas() {
        return repository.findAll();
    }

    @Operation(summary = "Obtener odontograma por ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Odontograma> getOdontogramasByOdontogramaId(@RequestBody @PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<Odontograma> getFichaTecnicaByPacienteId(@PathVariable Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    @Operation(summary = "Crear un nuevo odontograma")
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.OK)
    public void createOdontograma(@RequestBody Odontograma odontograma) {
        repository.save(odontograma);
    }

    @Operation(summary = "Subir imagen para odontograma")
    @PostMapping("/{id}/subirImg")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen subida con éxito",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "No se ha proporcionado ninguna imagen"),
            @ApiResponse(responseCode = "500", description = "Error al subir la imagen")
    })
    public ResponseEntity<String> subirImagen(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) {
        if (image == null) {
            return ResponseEntity.badRequest().body("No se ha proporcionado ninguna imagen");
        }
        try {
            Path subirPath = Paths.get(subida_dir);
            if (!Files.exists(subirPath)) {
                Files.createDirectories(subirPath);
            }

            String fileName = id + "_" + image.getOriginalFilename();
            Path filePath = subirPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath); // Usamos Files.move para mover el archivo correctamente

            Odontograma odontograma = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            odontograma.setImg_url(filePath.toString());
            repository.save(odontograma);

            return ResponseEntity.ok().body("Imagen subida con éxito: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("No se ha proporcionado ninguna imagen");
        }
    }

    @Operation(summary = "Obtener imagen del odontograma")
    @GetMapping("/{id}/imagen")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable("id") Long id) {
        Odontograma odontograma = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Path imagePath = Paths.get(odontograma.getImg_url());
        try {
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
