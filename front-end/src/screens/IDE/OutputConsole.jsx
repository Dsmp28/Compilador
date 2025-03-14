import React from "react";
import styled from "styled-components";

const StyledConsole = styled.div`
  width: 40%;
  background-color: #fafafa;
  overflow-y: auto;
    color: black;
`;

const OutputConsole = () => {
    return (
        <StyledConsole>
            <div className={"tituloSalida"}>
                <p className={"textoSalida"}>Salida:</p>
            </div>
            <p>Salida aquí...</p> {/* Aquí se mostrará la salida del código */}
        </StyledConsole>
    )
};

export default OutputConsole;
