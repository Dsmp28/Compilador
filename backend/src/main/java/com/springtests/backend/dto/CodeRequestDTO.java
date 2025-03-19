package com.springtests.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeRequestDTO {
    private String codigo;

    public CodeRequestDTO() {
    }

    public CodeRequestDTO(String codigo) {
        this.codigo = codigo;
    }
}

