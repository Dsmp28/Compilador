package com.springtests.backend.service;

import com.springtests.backend.entity.Proyecto;
import com.springtests.backend.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Obtener todos los proyectos
    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    // Obtener un proyecto por ID
    public Optional<Proyecto> getProyectoById(Long id) {
        return proyectoRepository.findById(id);
    }

    // Crear un nuevo proyecto
    public Proyecto createProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Actualizar un proyecto existente
    public Proyecto updateProyecto(Long id, Proyecto proyectoDetails) {
        return proyectoRepository.findById(id)
                .map(proyecto -> {
                    proyecto.setNombre(proyectoDetails.getNombre());
                    return proyectoRepository.save(proyecto);
                })
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    // Eliminar un proyecto
    public void deleteProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }
}
