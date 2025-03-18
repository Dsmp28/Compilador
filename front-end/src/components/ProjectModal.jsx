import React, { useState } from 'react';

const ProjectModal = ({ isOpen, onClose, onCreate }) => {
  const [projectName, setProjectName] = useState("");

  if (!isOpen) return null;

  const handleCreate = () => {
    if (projectName.trim() === "") return; // Opcional: evitar crear proyectos vacíos
    onCreate(projectName);
    setProjectName(""); // Reinicia el campo
  };

  return (
    <div className="modal">
      <h2 className="textoNuevoProyecto">Nuevo Proyecto</h2>
      <input 
        className="textFieldProyecto" 
        type="text" 
        placeholder="Nombre del proyecto" 
        value={projectName}
        onChange={(e) => setProjectName(e.target.value)}
      />
      <div className="modal-buttons">
        <button onClick={handleCreate} className="nuevoConfirmacion">Crear</button>
        <button onClick={onClose} className="nuevoConfirmacion">Cerrar</button>
      </div>
    </div>
  );
};

export default ProjectModal;
