package com.springtests.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportRequestDTO {
    private String fileName;
    private String content;

    public ExportRequestDTO() {}

    public ExportRequestDTO(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }
}
