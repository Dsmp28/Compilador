import "../styles/sidebar.css";
import logo from "../assets/codigo.png";
import Button from "./Button.jsx";
import ProjectModal from "./ProjectModal.jsx";

import React , { useState } from 'react';

const sidebar = () => {
    // Estado para controlar si el modal está abierto o cerrado
    const [isModalOpen, setIsModalOpen] = useState(false);
    // Función para abrir el modal
    const handleNewProject = () => {
        setIsModalOpen(true);  // Abre el modal
    };

    // Función para cerrar el modal
    const closeModal = () => {
        setIsModalOpen(false);  // Cierra el modal
    };

    //Función para crear el proyecto
    const createProject = () => {
        //Logica de crear el proyecto
        closeModal();
    }
    return (
        <aside className="sidebar">
            <div className="sidebar-logo">
                <img src={logo} alt="CompileX Logo" />
                <h2 className="sidebar-title">CompileX</h2>
            </div>
            <Button className={"new-project-btn"} text={"Crear un nuevo proyecto"} onClick={handleNewProject} />{
            <ProjectModal isOpen={isModalOpen} onClose={closeModal} onCreate={createProject} />
        }
        </aside>
    );
}
export default sidebar;