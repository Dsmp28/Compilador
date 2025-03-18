// AddFolderModal.jsx
import React, { useState } from 'react';
import styled from 'styled-components';

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
  fotn-size: 24px;
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

const AddFolderModal = ({ isOpen, onClose, onAddFolder }) => {
  const [folderName, setFolderName] = useState('');

  if (!isOpen) return null;

  const handleAdd = () => {
    if (folderName.trim()) {
      onAddFolder(folderName);
      setFolderName('');
      onClose();
    }
  };

  return (
    <Overlay>
      <ModalContainer>
        <ModalHeader>Agregar Nueva Carpeta</ModalHeader>
        <Input
          id="add-folder-input"
          type="text"
          placeholder="Nombre de la carpeta"
          value={folderName}
          onChange={(e) => setFolderName(e.target.value)}
        />
        <ButtonContainer>
          <Button onClick={onClose}>Cancelar</Button>
          <Button onClick={handleAdd}>Agregar</Button>
        </ButtonContainer>
      </ModalContainer>
    </Overlay>
  );
};

export default AddFolderModal;
