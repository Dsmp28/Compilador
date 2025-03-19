package com.springtests.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.Token;
import com.springtests.backend.dto.CodeRequestDTO;
import com.springtests.backend.dto.CodeResponseDTO;
import com.springtests.backend.service.RespuestaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/compilador")
public class CompiladorController {

    private RespuestaService respuestaService;
    
    @PostMapping
    public ResponseEntity<CodeResponseDTO> compilarCodigo (@RequestBody CodeRequestDTO codeRequestDTO) {
        String codigo = codeRequestDTO.getCodigo();

        respuestaService = new RespuestaService();
        respuestaService.analizarCodigo(codigo);

        List<Token> tokens = respuestaService.obtenerTokens();
        List<String> errores = respuestaService.obtenerErrores();
        SymbolTable symbolTable = respuestaService.obtenerTablaSimbolos();

        CodeResponseDTO codeResponseDTO = new CodeResponseDTO(tokens, symbolTable, errores);

        return ResponseEntity.ok(codeResponseDTO);
    }
    
}
