import React, { useState } from "react";
import styled from "styled-components";
import TopBar from "./TopBar.jsx";
import BottomBar from "./BottomBar.jsx";
import CodeMirror from "@uiw/react-codemirror";
import { myLanguageSupport } from "./myLanguage.js";

const StyledEditorContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 85%;
  height: 100%;
    overflow:hidden;
    
`;

const EditorWrapper = styled.div`
    flex: 1;
    background: #2d2d2d;
    padding: 10px;
    border: 1px solid #444;
    display: flex;
    flex-direction: column;;
`;

const CodeEditorWrapper = styled.div`
  flex-grow: 1; 
  overflow: auto;
    max-height:75vh;
`;

const BottomBarWrapper = styled.div`
  width: 100%;
  background: white;
  border-top: 2px solid #ddd;
  position: relative; 
  z-index: 10; 
`;

const CodeEditor = () => {
    const [theme, setTheme] = useState("dark");
    const [code, setCode] = useState("// Escribe tu código aquí...");

    const handleSave = () => console.log("Código guardado:", code); // Aquí se guarda el código
    const handleExport = () => console.log("Código exportado"); // Aquí se exporta el código
    const handleExecute = () => { //Aquí se ejecuta el código
        console.log("Ejecutando código...");
        console.log(code);
    }

    return (
        <StyledEditorContainer>
            <TopBar onSave={handleSave} onThemeChange={setTheme} />

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
                <BottomBar onExport={handleExport} onExecute={handleExecute} />
            </BottomBarWrapper>
        </StyledEditorContainer>
    );
};

export default CodeEditor;
