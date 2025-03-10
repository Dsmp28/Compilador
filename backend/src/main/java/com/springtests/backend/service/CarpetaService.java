package com.springtests.backend.service;

import com.springtests.backend.entity.Carpeta;
import com.springtests.backend.repository.CarpetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarpetaService {

    @Autowired
    private CarpetaRepository carpetaRepository;

    public List<Carpeta> getAllCarpetas() {
        return carpetaRepository.findAll();
    }

    public Optional<Carpeta> getCarpetaById(Long id) {
        return carpetaRepository.findById(id);
    }

    public Carpeta createCarpeta(Carpeta carpeta) {
        return carpetaRepository.save(carpeta);
    }

    public Carpeta updateCarpeta(Long id, Carpeta carpetaDetails) {
        return carpetaRepository.findById(id)
                .map(carpeta -> {
                    carpeta.setNombre(carpetaDetails.getNombre());
                    return carpetaRepository.save(carpeta);
                })
                .orElseThrow(() -> new RuntimeException("Carpeta no encontrada"));
    }

    public void deleteCarpeta(Long id) {
        carpetaRepository.deleteById(id);
    }
}
