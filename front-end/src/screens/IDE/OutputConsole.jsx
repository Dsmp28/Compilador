// src/components/OutputConsole.jsx
import React, { useContext, useState } from "react";
import styled from "styled-components";
import imagenFlecha from "../../assets/flechabajo.png";
import IdeContext from "../../context/IdeContext";

const StyledConsole = styled.div`
  width: 40%;
  background-color: #fafafa;
  color: black;
  overflow-y: auto;
  padding: 10px;
`;

const Section = styled.div`
  margin-bottom: 10px;
  border: 1px solid #ccc;
`;

const SectionHeader = styled.div`
  background-color: #f0f0f0;
  padding: 8px;
  cursor: pointer;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ArrowIcon = styled.img`
  width: 20px;
  height: 20px;
  transition: transform 0.3s ease;
  transform: ${({ rotated }) => (rotated ? "rotate(180deg)" : "rotate(0deg)")};
`;

const SectionContent = styled.div`
  padding: 8px;
  display: ${({ expanded }) => (expanded ? "block" : "none")};
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

const TableHeader = styled.th`
  border: 1px solid #ccc;
  padding: 4px 8px;
  background-color: #e0e0e0;
`;

const TableCell = styled.td`
  border: 1px solid #ccc;
  padding: 4px 8px;
`;

const OutputConsole = () => {
  // Obtén la respuesta del compilador desde IdeContext
  const { codeResponse } = useContext(IdeContext);

  // Extraer tokens, errores y símbolos desde codeResponse
  const tokens = codeResponse?.tokens || [];
  const errors = codeResponse?.errors || [];
  // Suponemos que la tabla de símbolos se encuentra en symbolTable.rows
  const symbols =
    codeResponse?.symbolTable && codeResponse.symbolTable.rows
      ? codeResponse.symbolTable.rows
      : [];

  const [sections, setSections] = useState({
    tokens: false,
    errors: false,
    simbolos: false,
  });

  const toggleSection = (section) => {
    setSections((prev) => ({
      ...prev,
      [section]: !prev[section],
    }));
  };

  return (
    <StyledConsole>
      <div className="tituloSalida">
        <p className="textoSalida">Salida:</p>
      </div>

      {/* Sección de Tokens */}
      <Section>
        <SectionHeader onClick={() => toggleSection("tokens")}>
          Lista de Tokens
          <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.tokens} />
        </SectionHeader>
        <SectionContent expanded={sections.tokens}>
          {tokens.length > 0 ? (
            <Table>
              <thead>
                <tr>
                  <TableHeader>Lexema</TableHeader>
                  <TableHeader>Tipo</TableHeader>
                  <TableHeader>Línea</TableHeader>
                  <TableHeader>Columna</TableHeader>
                </tr>
              </thead>
              <tbody>
                {tokens.map((token, index) => (
                  <tr key={index}>
                    <TableCell>{token.lexeme}</TableCell>
                    <TableCell>{token.type}</TableCell>
                    <TableCell>{token.line || "-"}</TableCell>
                    <TableCell>{token.column || "-"}</TableCell>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <p>No hay tokens</p>
          )}
        </SectionContent>
      </Section>

      {/* Sección de Errores */}
      <Section>
        <SectionHeader onClick={() => toggleSection("errors")}>
          Lista de Errores
          <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.errors} />
        </SectionHeader>
        <SectionContent expanded={sections.errors}>
          {errors.length > 0 ? (
            errors.map((error, index) => <p key={index}>{error}</p>)
          ) : (
            <p>No hay errores</p>
          )}
        </SectionContent>
      </Section>

      {/* Sección de Tabla de Símbolos */}
      <Section>
        <SectionHeader onClick={() => toggleSection("simbolos")}>
          Tabla de Símbolos
          <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.simbolos} />
        </SectionHeader>
        <SectionContent expanded={sections.simbolos}>
          {symbols.length > 0 ? (
            <Table>
              <thead>
                <tr>
                  <TableHeader>Index</TableHeader>  
                  <TableHeader>Identificador</TableHeader>
                  <TableHeader>Tipo</TableHeader>
                  <TableHeader>Linea</TableHeader>
                  <TableHeader>Columna</TableHeader>
                </tr>
              </thead>
              <tbody>
                {symbols.map((symbol, index) => (
                  <tr key={index}>
                    <TableCell>{index}</TableCell>
                    <TableCell>{symbol.identifier || symbol.nombre}</TableCell>
                    <TableCell>{symbol.tokenType || symbol.tipo}</TableCell>
                    <TableCell>{symbol.line || "-"}</TableCell>
                    <TableCell>{symbol.column || "-"}</TableCell>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <p>No hay símbolos</p>
          )}
        </SectionContent>
      </Section>
    </StyledConsole>
  );
};

export default OutputConsole;
