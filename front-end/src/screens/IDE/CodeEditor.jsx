// src/components/CodeEditor.jsx
import React, { useState, useContext, useEffect } from "react";
import styled from "styled-components";
import TopBar from "./TopBar.jsx";
import BottomBar from "./BottomBar.jsx";
import CodeMirror from "@uiw/react-codemirror";
import { myLanguageSupport } from "./myLanguage.js";
import HomeContext from "../../context/HomeContext.jsx";

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

  // Obtener updateFile desde el contexto
  const { updateFile } = useContext(HomeContext);

  // Cuando cambie initialContent o fileName, actualizamos el estado local.
  useEffect(() => {
    setCode(initialContent);
    setCurrentFileName(fileName);
  }, [initialContent, fileName]);

  // Manejar el guardado: actualizar el archivo con el nuevo nombre y contenido
  const handleSave = async () => {
    console.log("Guardando código:", code);
    try {
      if (fileId) {
        await updateFile(fileId, currentFileName, code);
        console.log("Archivo actualizado"); //Agregar mensaje de guardar
      } else {
        console.warn("No se encontró fileId");
      }
    } catch (error) {
      console.error("Error al guardar archivo:", error);
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
        <BottomBar onExport={() => console.log("Exportar código")} onExecute={() => console.log("Ejecutar código", code)} />
      </BottomBarWrapper>
    </StyledEditorContainer>
  );
};

export default CodeEditor;
