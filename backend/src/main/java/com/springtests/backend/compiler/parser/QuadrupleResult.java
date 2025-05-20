package com.springtests.backend.compiler.parser;

import java.util.List;
import java.util.Map;

public record QuadrupleResult(
        List<String> errors,
        Map<String, Double> memory,
        Double value,
        List<Quadruple> intermediateCode
) {}