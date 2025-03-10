package com.springtests.backend.controller;

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
}
