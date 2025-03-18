import React, { useState } from "react";
import styled from "styled-components";
import imagenFlecha from "../../assets/flechabajo.png";


const StyledConsole = styled.div`
    width: 40%;
    background-color: #fafafa;
    color: black;
    overflow-y: auto;
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

const OutputConsole = () => {
    const [sections, setSections] = useState({
        tokens: false,
        errores: false,
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

            <Section>
                <SectionHeader onClick={() => toggleSection("tokens")}>
                    Lista de Tokens
                    <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.tokens} />
                </SectionHeader>
                <SectionContent expanded={sections.tokens}>
                    {/* Aquí deben de ir los tokens*/}
                    <p>Token1</p>
                    <p>Token2</p>
                </SectionContent>
            </Section>

            <Section>
                <SectionHeader onClick={() => toggleSection("errores")}>
                    Lista de Errores
                    <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.errores} />
                </SectionHeader>
                <SectionContent expanded={sections.errores}>
                    {/* Aquí deben de ir los errores*/}
                    <p>Error línea 1: símbolo inválido</p>
                    <p>Error línea 5: número mal formado</p>
                </SectionContent>
            </Section>

            <Section>
                <SectionHeader onClick={() => toggleSection("simbolos")}>
                    Tabla de Símbolos
                    <ArrowIcon src={imagenFlecha} alt="Icono" rotated={sections.simbolos} />
                </SectionHeader>
                <SectionContent expanded={sections.simbolos}>
                    {/* Aquí deben de ir los símbolos*/}
                    <p>ejemplo | ID | 3 | 5</p>
                </SectionContent>
            </Section>
        </StyledConsole>
    );
};

export default OutputConsole;
