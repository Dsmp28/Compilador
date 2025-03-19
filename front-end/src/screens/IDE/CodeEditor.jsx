// src/components/CodeEditor.jsx
import React, { useState, useContext, useEffect } from "react";
import styled from "styled-components";
import TopBar from "./TopBar.jsx";
import BottomBar from "./BottomBar.jsx";
import CodeMirror from "@uiw/react-codemirror";
import { myLanguageSupport } from "./myLanguage.js";
import IdeContext from "../../context/IdeContext";
import { HomeContext } from "../../context/HomeContext.jsx";

const StyledEditorContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 85%;
  height: 100%;
  overflow: hidden;
`;

const EditorWrapper = styled.div`
  flex: 1;
  background: #2d2d2d;
  padding: 10px;
  border: 1px solid #444;
  display: flex;
  flex-direction: column;
`;

const CodeEditorWrapper = styled.div`
  flex-grow: 1;
  overflow: auto;
  max-height: 75vh;
`;

const BottomBarWrapper = styled.div`
  width: 100%;
  background: white;
  border-top: 2px solid #ddd;
  position: relative;
  z-index: 10;
`;

const CodeEditor = ({ initialContent, fileName, fileId }) => {
  const [theme, setTheme] = useState("dark");
  const [code, setCode] = useState("");
  const [currentFileName, setCurrentFileName] = useState(fileName);

  // Obtener funciones del contexto para actualizar el archivo y compilar el código
  const { updateFile } = useContext(HomeContext);
  const { compileCode, handleExport } = useContext(IdeContext);

  // Actualiza el estado local si initialContent o fileName cambian
  useEffect(() => {
    setCode(initialContent);
    setCurrentFileName(fileName);
  }, [initialContent, fileName]);

  // Guardar el archivo actual (actualiza el backend)
  const handleSave = async () => {
    console.log("Guardando código:", code);
    try {
      if (fileId) {
        await updateFile(fileId, currentFileName, code);
        console.log("Archivo actualizado");
      } else {
        console.warn("No se encontró fileId");
      }
    } catch (error) {
      console.error("Error al guardar archivo:", error);
    }
  };

  // Ejecutar el código: envía el código actual al backend para compilarlo
  const handleExecute = async () => {
    try {
      const response = await compileCode(code);
      console.log("Respuesta del compilador:", response);
    } catch (error) {
      console.error("Error al ejecutar código:", error);
    }
  };

  return (
    <StyledEditorContainer>
      <TopBar onSave={handleSave} onThemeChange={setTheme} fileName={currentFileName} />
      <EditorWrapper>
        <CodeEditorWrapper>
          <CodeMirror
            value={code}
            height="100%"
            extensions={[myLanguageSupport()]}
            theme={theme}
            onChange={(value) => setCode(value)}
          />
        </CodeEditorWrapper>
      </EditorWrapper>
      <BottomBarWrapper>
        <BottomBar 
          onExport={() =>  handleExport(code, currentFileName)}
          onExecute={handleExecute} 
        />
      </BottomBarWrapper>
    </StyledEditorContainer>
  );
};

export default CodeEditor;

