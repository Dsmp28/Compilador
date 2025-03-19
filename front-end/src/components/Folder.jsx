// src/components/Folder.jsx
import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import Button from "./Button";
import carpeta from "../assets/carpeta.png";
import agregar from "../assets/agregar.png";
import editar from "../assets/editar.png";
import eliminar from "../assets/eliminar.png";
import expandir from "../assets/flecharriba.png";
import contraer from "../assets/flechabajo.png";
import File from "./File";
import FileModal from "./FileModal";
import { HomeContext } from "../context/HomeContext";

const Folder = ({ folder }) => {
  const navigate = useNavigate();

  // Estados para controlar edición y expansión
  const [isEditing, setIsEditing] = useState(false);
  const [newFolderName, setNewFolderName] = useState(folder.name);
  const [isExpanded, setIsExpanded] = useState(false);

  // Estado para el modal de agregar archivo
  const [isFileModalOpen, setIsFileModalOpen] = useState(false);

  // Extraemos las funciones necesarias del contexto
  const { updateFolder, deleteFolder, createFile, fetchProjects } = useContext(HomeContext);

  // Alternar la visibilidad de la carpeta
  const toggleFolder = () => {
    setIsExpanded((prev) => !prev);
  };

  // Iniciar edición de la carpeta
  const handleEditFolder = () => {
    setIsEditing(true);
  };

  // Guardar la edición del nombre de la carpeta
  const handleSaveFolder = async () => {
    if (newFolderName.trim()) {
      try {
        // Se asume que el objeto folder tiene la propiedad 'proyecto' con el id del proyecto
        await updateFolder(folder.id, newFolderName);
        setIsEditing(false);
        await fetchProjects();
      } catch (error) {
        console.error("Error al actualizar carpeta:", error);
      }
    }
  };

  // Cancelar la edición y restaurar el nombre original
  const handleCancelEdit = () => {
    setIsEditing(false);
    setNewFolderName(folder.name);
  };

  // Eliminar la carpeta
  const handleDeleteFolder = async () => {
    if (window.confirm("¿Estás seguro de que deseas eliminar esta carpeta?")) {
      try {
        await deleteFolder(folder.id);
        await fetchProjects();
      } catch (error) {
        console.error("Error al eliminar carpeta:", error);
      }
    }
  };

  // Abrir modal para agregar un archivo
  const openFileModal = () => {
    setIsFileModalOpen(true);
  };

  const closeFileModal = () => {
    setIsFileModalOpen(false);
  };

  // Manejar la creación de un archivo
  // Se espera que onAddFile reciba un objeto { name, content }
  const handleAddFile = async (fileData) => {
    try {
      await createFile(folder.id, fileData.name, fileData.content);
      await fetchProjects();
    } catch (error) {
      console.error("Error al crear archivo:", error);
    } finally {
      closeFileModal();
    }
  };

  // Navegar a la vista de edición/visualización de un archivo
  const abrirArchivo = (file) => {
    navigate("/editor", { state: { fileName: file.name, fileContent: file.content, fileId: file.id } });
  };

  return (
    <div className="folder-container">
      <div className="folder-header">
        <div className="folder-info">
          <img src={carpeta} alt="Carpeta" className="folder-icon" />
          {isEditing ? (
            <div style={{ display: "flex", alignItems: "center" }}>
              <input
                type="text"
                value={newFolderName}
                onChange={(e) => setNewFolderName(e.target.value)}
                autoFocus
                style={{ marginRight: "8px" }}
              />
              <Button title="Guardar" onClick={handleSaveFolder} className="btn-small">
                Guardar
              </Button>
              <Button title="Cancelar" onClick={handleCancelEdit} className="btn-small">
                Cancelar
              </Button>
            </div>
          ) : (
            <span className="folder-name">{folder.name}</span>
          )}
        </div>
        <div className="folder-actions">
          <Button
            icon={agregar}
            title="Nuevo Archivo"
            onClick={openFileModal}
            className="btn-icon"
          >
            <img src={agregar} alt="Nuevo Archivo" />
          </Button>
          <Button
            icon={editar}
            title="Editar"
            onClick={handleEditFolder}
            className="btn-icon"
          >
            <img src={editar} alt="Editar" />
          </Button>
          <Button
            icon={eliminar}
            title="Eliminar"
            onClick={handleDeleteFolder}
            className="btn-icon"
          >
            <img src={eliminar} alt="Eliminar" />
          </Button>
          <Button
            icon={isExpanded ? expandir : contraer}
            title={isExpanded ? "Contraer" : "Expandir"}
            onClick={toggleFolder}
            className="btn-icon"
          >
            <img
              src={isExpanded ? expandir : contraer}
              alt={isExpanded ? "Contraer" : "Expandir"}
            />
          </Button>
        </div>
      </div>

      {isExpanded && folder.files && folder.files.length > 0 && (
        <div className="files-container">
          {folder.files.map((file) => (
            <File key={file.id} file={file} onClick={abrirArchivo} />
          ))}
        </div>
      )}

      {/* Modal para agregar un nuevo archivo */}
      <FileModal isOpen={isFileModalOpen} onClose={closeFileModal} onAddFile={handleAddFile} />
    </div>
  );
};

export default Folder;

