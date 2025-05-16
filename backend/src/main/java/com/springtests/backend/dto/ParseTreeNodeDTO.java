package com.springtests.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParseTreeNodeDTO {
    private String name;
    private List<ParseTreeNodeDTO> children = new ArrayList<>();

    public ParseTreeNodeDTO(String name) {
        this.name = name;
    }

    public ParseTreeNodeDTO(String name, List<ParseTreeNodeDTO> children) {
        this.name = name;
        this.children = children;
    }
}
