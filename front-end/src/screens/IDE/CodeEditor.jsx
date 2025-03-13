import React, { useState } from "react";
import styled from "styled-components";
import TopBar from "./TopBar.jsx";
import BottomBar from "./BottomBar.jsx";

const StyledEditorContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const EditorArea = styled.div`
    flex: 1;
    background: #2d2d2d;
    padding: 20px;
    color: white;
    font-family: monospace;
`;

const CodeEditor = () => {
    const [theme, setTheme] = useState("dark");
    const [code, setCode] = useState("// Escribe tu código aquí...");

    const handleSave = () => console.log("Código guardado:", code);
    const handleExport = () => console.log("Código exportado");
    const handleExecute = () => console.log("Ejecutando código...");

    return (
        <StyledEditorContainer>
            <TopBar onSave={handleSave} onThemeChange={setTheme} />
            <EditorArea>{code}</EditorArea>
            <BottomBar onExport={handleExport} onExecute={handleExecute} />
        </StyledEditorContainer>
    );
};

export default CodeEditor;
