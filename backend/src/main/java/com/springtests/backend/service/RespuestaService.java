package com.springtests.backend.service;

import com.springtests.backend.entity.Respuesta;
import com.springtests.backend.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    public List<Respuesta> getAllRespuestas() {
        return respuestaRepository.findAll();
    }

    public Optional<Respuesta> getRespuestaById(Long id) {
        return respuestaRepository.findById(id);
    }

    public Respuesta createRespuesta(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public Respuesta updateRespuesta(Long id, Respuesta respuestaDetails) {
        return respuestaRepository.findById(id)
                .map(respuesta -> {
                    respuesta.setMensaje(respuestaDetails.getMensaje());
                    respuesta.setExito(respuestaDetails.getExito());
                    return respuestaRepository.save(respuesta);
                })
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));
    }

    public void deleteRespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }
}
