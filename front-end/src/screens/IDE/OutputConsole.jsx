// src/components/OutputConsole.jsx
import React, { useContext, useState } from "react";
import styled from "styled-components";
import imagenFlecha from "../../assets/flechabajo.png";
import IdeContext from "../../context/IdeContext";
import TreeVisualizer from "../../components/treeVisualizer.jsx";

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

const generatePythonTutorURL = (codeLines) => {
  const joinedCode = codeLines.join("\n");
  const encoded = encodeURIComponent(joinedCode);
  return `https://pythontutor.com/visualize.html#code=${encoded}&cumulative=false&heapPrimitives=false&textReferences=false&py=3&curInstr=0`;
};

const OutputConsole = () => {
  // Obtén la respuesta del compilador desde IdeContext
  const { codeResponse } = useContext(IdeContext);
  // Extraer valores en memoria, errores y el arbol
  const memory = codeResponse?.memory || [];
  const errors = codeResponse?.errors || [];
  const tree = codeResponse?.tree || [];
  const pythonCode = codeResponse?.pythonCode || [];

  const [sections, setSections] = useState({
    memory: false,
    errors: false,
    tree: false,
    intermediate: false
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
        <p className="textoSalida">Salida</p>
      </div>

      {/* Sección de Memory */}
      <Section>
        <SectionHeader onClick={() => toggleSection("memory")}>
          Valores en memoria
          <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.memory} />
        </SectionHeader>
        <SectionContent expanded={sections.memory}>
          {Object.keys(memory).length > 0 ? (
            <Table>
              <thead>
                <tr>
                  <TableHeader>Variable</TableHeader>
                  <TableHeader>Valor</TableHeader>
                </tr>
              </thead>
              <tbody>
                {Object.entries(memory).map(([key, value], index) => (
                  <tr key={index}>
                    <TableCell>{key}</TableCell>
                    <TableCell>{value}</TableCell>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <p>No hay valores en memoria.</p>
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
      {tree && (
          <Section>
            <SectionHeader onClick={() => toggleSection("tree")}>
              Árbol de Sintaxis
              <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.tree} />
            </SectionHeader>
            <SectionContent expanded={sections.tree}>
              <TreeVisualizer data={tree} />
            </SectionContent>
          </Section>
      )}
      {/* Sección de codigo intermedio */}
      <Section>
        <SectionHeader onClick={() => toggleSection("intermediate")}>
          Codigo intermedio
          <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.intermediate} />
        </SectionHeader>
        <SectionContent expanded={sections.intermediate}>
          {pythonCode.length > 0 ? (
              <>
                <pre>
                  {pythonCode.map((linea, index) => (
                      <div key={index}>{linea}</div>
                  ))}
                </pre>
                <button
                    onClick={() => window.open(generatePythonTutorURL(pythonCode), "_blank")}
                >
                  Ejecutar en Python Tutor
                </button>
              </>
          ) : (
              <p>No hay código intermedio.</p>
          )}
        </SectionContent>
      </Section>
    </StyledConsole>
  );
};

export default OutputConsole;
