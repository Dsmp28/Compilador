package com.springtests.backend.controller;

import com.springtests.backend.dto.ExportRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.springtests.backend.entity.Archivo;
import com.springtests.backend.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping
    public List<Archivo> getAllArchivos() {
        return archivoService.getAllArchivos();
    }

    @GetMapping("/{id}")
    public Optional<Archivo> getArchivoById(@PathVariable Long id) {
        return archivoService.getArchivoById(id);
    }

    @PostMapping
    public Archivo createArchivo(@RequestBody Archivo archivo) {
        return archivoService.createArchivo(archivo);
    }

    @PutMapping("/{id}")
    public Archivo updateArchivo(@PathVariable Long id, @RequestBody Archivo archivo) {
        return archivoService.updateArchivo(id, archivo);
    }

    @DeleteMapping("/{id}")
    public void deleteArchivo(@PathVariable Long id) {
        archivoService.deleteArchivo(id);
    }
    @PostMapping("/export")
        public ResponseEntity<String> exportArchivo(@RequestBody ExportRequestDTO exportRequest) {
            try {
                String fileName = exportRequest.getFileName();
                String content = exportRequest.getContent();

                java.nio.file.Path exportDir = java.nio.file.Paths.get("exports");
                if (!java.nio.file.Files.exists(exportDir)) {
                    java.nio.file.Files.createDirectories(exportDir);
                }

                java.nio.file.Path filePath = exportDir.resolve(fileName + ".txt");

                java.nio.file.Files.write(filePath, content.getBytes(java.nio.charset.StandardCharsets.UTF_8));

                return ResponseEntity.ok("Archivo exportado exitosamente en: " + filePath.toAbsolutePath().toString());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al exportar el archivo: " + e.getMessage());
            }
        }
}
