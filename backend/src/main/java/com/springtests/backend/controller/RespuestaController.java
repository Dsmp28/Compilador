package com.springtests.backend.controller;

import com.springtests.backend.entity.Respuesta;
import com.springtests.backend.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public List<Respuesta> getAllRespuestas() {
        return respuestaService.getAllRespuestas();
    }

    @GetMapping("/{id}")
    public Optional<Respuesta> getRespuestaById(@PathVariable Long id) {
        return respuestaService.getRespuestaById(id);
    }

    @PostMapping
    public Respuesta createRespuesta(@RequestBody Respuesta respuesta) {
        return respuestaService.createRespuesta(respuesta);
    }

    @PutMapping("/{id}")
    public Respuesta updateRespuesta(@PathVariable Long id, @RequestBody Respuesta respuesta) {
        return respuestaService.updateRespuesta(id, respuesta);
    }

    @DeleteMapping("/{id}")
    public void deleteRespuesta(@PathVariable Long id) {
        respuestaService.deleteRespuesta(id);
    }
}
