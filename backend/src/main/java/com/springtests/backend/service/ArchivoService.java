package com.springtests.backend.service;

import com.springtests.backend.entity.Archivo;
import com.springtests.backend.repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public List<Archivo> getAllArchivos() {
        return archivoRepository.findAll();
    }

    public Optional<Archivo> getArchivoById(Long id) {
        return archivoRepository.findById(id);
    }

    public Archivo createArchivo(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    public Archivo updateArchivo(Long id, Archivo archivoDetails) {
        return archivoRepository.findById(id)
                .map(archivo -> {
                    archivo.setNombre(archivoDetails.getNombre());
                    archivo.setContenido(archivoDetails.getContenido());
                    return archivoRepository.save(archivo);
                })
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    public void deleteArchivo(Long id) {
        archivoRepository.deleteById(id);
    }
}
