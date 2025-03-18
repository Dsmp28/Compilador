// src/components/Sidebar.jsx
import "../styles/sidebar.css";
import logo from "../assets/codigo.png";
import Button from "./Button.jsx";
import ProjectModal from "./ProjectModal.jsx";
import React, { useState, useContext } from "react";
import { HomeContext } from "../context/HomeContext";

const Sidebar = () => {
  // Estado para controlar si el modal está abierto o cerrado
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Obtener las funciones para crear y, si es necesario, refrescar proyectos desde el contexto
  const { createProject, fetchProjects } = useContext(HomeContext);

  // Función para abrir el modal
  const handleNewProject = () => {
    setIsModalOpen(true);
  };

  // Función para cerrar el modal
  const closeModal = () => {
    setIsModalOpen(false);
  };

  // Función para crear el proyecto utilizando el contexto
  // Se recibe el nombre del proyecto desde el modal
  const createNewProject = async (projectName) => {
    try {
      await createProject(projectName);
      await fetchProjects();
    } catch (error) {
      console.error("Error al crear el proyecto:", error);
    } finally {
      closeModal();
    }
  };

  return (
    <aside className="sidebar">
      <div className="sidebar-logo">
        <img src={logo} alt="CompileX Logo" />
        <h2 className="sidebar-title">CompileX</h2>
      </div>
      <Button
        className="new-project-btn"
        text="Crear un nuevo proyecto"
        onClick={handleNewProject}
      />
      <ProjectModal
        isOpen={isModalOpen}
        onClose={closeModal}
        onCreate={createNewProject}
      />
    </aside>
  );
};

export default Sidebar;
