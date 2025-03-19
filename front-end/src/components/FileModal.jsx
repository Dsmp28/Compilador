// src/components/FileModal.jsx
import React, { useState } from "react";
import styled from "styled-components";

const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0,0,0,0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
`;

const ModalContainer = styled.div`
  background-color: #fff;
  padding: 24px;
  border-radius: 8px;
  width: 90%;
  max-width: 400px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
`;

const ModalHeader = styled.h2`
  margin-top: 0;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 24px;
  color: #333;
`;

const Input = styled.input`
  width: 100%;
  padding: 8px;
  margin: 12px 0;
  font-size: 16px;
`;

const TextArea = styled.textarea`
  width: 100%;
  padding: 8px;
  margin: 12px 0;
  font-size: 16px;
  resize: vertical;
`;

const DividerContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 16px 0;
`;

const DividerLine = styled.div`
  flex-grow: 1;
  height: 1px;
  background-color: #ccc;
`;

const DividerText = styled.span`
  margin: 0 8px;
  font-weight: bold;
  color: #666;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
`;

const Button = styled.button`
  padding: 8px 16px;
  margin-left: 8px;
  font-size: 14px;
  cursor: pointer;
`;

const FileModal = ({ isOpen, onClose, onAddFile }) => {
  const [fileName, setFileName] = useState("");
  const [fileContent, setFileContent] = useState("");
  const [imported, setImported] = useState(false);

  if (!isOpen) return null;

  // Manejar la importación de un archivo .txt
  const handleFileImport = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (event) => {
        // Se asigna el contenido importado
        const content = event.target.result;
        setFileContent(content);
        // Se asigna el nombre del archivo importado al input de nombre
        setFileName(file.name);
        setImported(true);
      };
      reader.readAsText(file);
    }
  };

  const handleAdd = () => {
    if (fileName.trim()) {
      onAddFile({ name: fileName, content: fileContent });
      setFileName("");
      setFileContent("");
      setImported(false);
      onClose();
    }
  };

  return (
    <Overlay>
      <ModalContainer>
        <ModalHeader>Agregar archivo</ModalHeader>
        <Input
          type="text"
          placeholder="Nombre del archivo"
          value={fileName}
          onChange={(e) => setFileName(e.target.value)}
          style={{
            padding: '12px',
            borderRadius: '8px',
            border: '1px solid #ccc',
            fontSize: '16px',
            width: '90%',
            marginBottom: '20px',
            color: '#000000',
            backgroundColor: '#f9f9f9'
          }}
        />
        <TextArea
          placeholder="Contenido del archivo (opcional)"
          value={fileContent}
          onChange={(e) => setFileContent(e.target.value)}
          rows="5"
          style={{
            padding: '12px',
            borderRadius: '8px',
            border: '1px solid #ccc',
            fontSize: '16px',
            width: '90%',
            marginBottom: '20px',
            color: '#000000',
            backgroundColor: '#f9f9f9'
          }}
        />
        <DividerContainer>
          <DividerLine />
          <DividerText>o</DividerText>
          <DividerLine />
        </DividerContainer>
        <div>
          <label htmlFor="file-input">Importar archivo (.txt): </label>
          <Input 
            id="file-input"
            type="file"
            accept=".txt"
            onChange={handleFileImport}
            style={{
              color: '#000000',
            }}
          />
        </div>
        <ButtonContainer>
          <Button onClick={onClose} className={"new-project-btn"}>Cancelar</Button>
          <Button onClick={handleAdd} className={"new-project-btn"}>Agregar</Button>
        </ButtonContainer>
      </ModalContainer>
    </Overlay>
  );
};

export default FileModal;
