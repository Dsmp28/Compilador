package com.springtests.backend.controller;

import com.springtests.backend.entity.Carpeta;
import com.springtests.backend.service.CarpetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carpetas")
public class CarpetaController {

    @Autowired
    private CarpetaService carpetaService;

    @GetMapping
    public List<Carpeta> getAllCarpetas() {
        return carpetaService.getAllCarpetas();
    }

    @GetMapping("/{id}")
    public Optional<Carpeta> getCarpetaById(@PathVariable Long id) {
        return carpetaService.getCarpetaById(id);
    }

    @PostMapping
    public Carpeta createCarpeta(@RequestBody Carpeta carpeta) {
        return carpetaService.createCarpeta(carpeta);
    }

    @PutMapping("/{id}")
    public Carpeta updateCarpeta(@PathVariable Long id, @RequestBody Carpeta carpeta) {
        return carpetaService.updateCarpeta(id, carpeta);
    }

    @DeleteMapping("/{id}")
    public void deleteCarpeta(@PathVariable Long id) {
        carpetaService.deleteCarpeta(id);
    }
}
