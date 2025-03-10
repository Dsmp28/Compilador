import { useState } from "react";
import Folder from "./Folder";
import Button from "./Button";
import proyectoImagen from "../assets/proyecto.png";
import agregar from "../assets/agregar.png";
import editar from "../assets/editar.png";
import eliminar from "../assets/eliminar.png";
import contraer from "../assets/flechabajo.png";
import expandir from "../assets/flecharriba.png";

const ProjectList = ({ projects }) => {
    const [expandedProjects, setExpandedProjects] = useState({});

    const toggleProject = (projectId) => {
        setExpandedProjects((prev) => ({
            ...prev,
            [projectId]: !prev[projectId], // Alterna entre expandido y contraído
        }));
    };

    const crearCarpeta = () => {
        console.log("Crear carpeta"); // Lógica para crear una carpeta
    }
    const editarProyecto = () => {
        console.log("Editar proyecto"); // Lógica para editar un proyecto
    }
    const eliminarProyecto = () => {
        console.log("Eliminar proyecto"); // Lógica para eliminar un proyecto
    }
    return (
        <div>
            {projects.map((project) => {
                const isExpanded = expandedProjects[project.id]; // Estado actual del proyecto

                return (
                    <div key={project.id} className="project-container">
                        <div className="project-header">
                            <div className="project-info">
                                <img src={proyectoImagen} alt="Proyecto" width="24" height="24" />
                                <h3 className="tituloProyecto">{project.name}</h3>
                            </div>

                            <div className="project-actions">
                                <Button icon = {agregar} title="+Nueva Carpeta" onClick={crearCarpeta} className="btn-icon">
                                    <img src={agregar} alt="Nueva Carpeta" />
                                </Button>
                                <Button icon = {editar} title="Editar" onClick={editarProyecto} className="btn-icon">
                                    <img src={editar} alt="Editar" />
                                </Button>
                                <Button icon = {eliminar} title="Eliminar" onClick={eliminarProyecto} className="btn-icon">
                                    <img src={eliminar} alt="Eliminar" />
                                </Button>
                                <Button
                                    icon={isExpanded ? expandir : contraer}
                                    title={isExpanded ? "Contraer" : "Expandir"}
                                    onClick={() => toggleProject(project.id)}
                                    className="btn-icon">
                                </Button>
                            </div>
                        </div>

                        {isExpanded && (
                            <div className="folders">
                                {project.folders.map((folder) => (
                                    <Folder key={folder.id} folder={folder} />
                                ))}
                            </div>
                        )}
                    </div>
                );
            })}
        </div>
    );
};

export default ProjectList;
