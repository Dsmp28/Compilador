import "../styles/homeList.css";
import { useState, useContext } from "react";
import Folder from "./Folder";
import Button from "./Button";
import proyectoImagen from "../assets/proyecto.png";
import agregar from "../assets/agregar.png";
import editar from "../assets/editar.png";
import eliminar from "../assets/eliminar.png";
import contraer from "../assets/flechabajo.png";
import expandir from "../assets/flecharriba.png";
import HomeContext from "../context/HomeContext";

const ProjectList = ({ projects }) => {
    const [expandedProjects, setExpandedProjects] = useState({});
    const [editingProjectId, setEditingProjectId] = useState(null);
    const [newProjectName, setNewProjectName] = useState("");
    const { updateProject, deleteProject, fetchProjects } = useContext(HomeContext);

    const toggleProject = (projectId) => {
        setExpandedProjects((prev) => ({
            ...prev,
            [projectId]: !prev[projectId], // Alterna entre expandido y contraído
        }));
    };

    const handleEditClick = (project) => {
        setEditingProjectId(project.id);
        setNewProjectName(project.nombre);
    };

    const handleSaveEdit = async (projectId) => {
        if (newProjectName.trim()) {
            await updateProject(projectId, newProjectName);
            setEditingProjectId(null);
            setNewProjectName("");
            await fetchProjects();
        }
    };

    const handleCancelEdit = () => {
        setEditingProjectId(null);
        setNewProjectName("");
    };

    const handleDeleteProject = async (projectId) => {
        if (window.confirm("¿Estás seguro de que deseas eliminar este proyecto?")) {
            await deleteProject(projectId);
            await fetchProjects();
        }
    };

    const crearCarpeta = (projectId) => {
        console.log("Crear carpeta para el proyecto", projectId);
        // Aquí implementarías la lógica para crear una carpeta dentro del proyecto
    };

    return (
        <div>
            {projects && projects.length > 0 ? (
                projects.map((project) => {
                    const isExpanded = expandedProjects[project.id]; // Estado actual del proyecto
                    const isEditing = editingProjectId === project.id;

                    return (
                        <div key={project.id} className="project-container">
                            <div className="project-header">
                                <div className="project-info">
                                    <img src={proyectoImagen} alt="Proyecto" width="24" height="24" />
                                    
                                    {isEditing ? (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            <input
                                                type="text"
                                                value={newProjectName}
                                                onChange={(e) => setNewProjectName(e.target.value)}
                                                autoFocus
                                                style={{ marginRight: '8px' }}
                                            />
                                            <Button 
                                                title="Guardar" 
                                                onClick={() => handleSaveEdit(project.id)}
                                                className="btn-small"
                                            >
                                                Guardar
                                            </Button>
                                            <Button 
                                                title="Cancelar" 
                                                onClick={handleCancelEdit}
                                                className="btn-small"
                                            >
                                                Cancelar
                                            </Button>
                                        </div>
                                    ) : (
                                        <h3 className="tituloProyecto">{project.nombre}</h3> 
                                    )}
                                </div>

                                <div className="project-actions">
                                    <Button 
                                        icon={agregar} 
                                        title="+Nueva Carpeta" 
                                        onClick={() => crearCarpeta(project.id)} 
                                        className="btn-icon"
                                    >
                                        <img src={agregar} alt="Nueva Carpeta" />
                                    </Button>
                                    
                                    {!isEditing && (
                                        <>
                                            <Button 
                                                icon={editar} 
                                                title="Editar" 
                                                onClick={() => handleEditClick(project)} 
                                                className="btn-icon"
                                            >
                                                <img src={editar} alt="Editar" />
                                            </Button>
                                            <Button 
                                                icon={eliminar} 
                                                title="Eliminar" 
                                                onClick={() => handleDeleteProject(project.id)} 
                                                className="btn-icon"
                                            >
                                                <img src={eliminar} alt="Eliminar" />
                                            </Button>
                                        </>
                                    )}
                                    
                                    <Button
                                        icon={isExpanded ? expandir : contraer}
                                        title={isExpanded ? "Contraer" : "Expandir"}
                                        onClick={() => toggleProject(project.id)}
                                        className="btn-icon"
                                    >
                                        <img src={isExpanded ? expandir : contraer} alt={isExpanded ? "Contraer" : "Expandir"} />
                                    </Button>
                                </div>
                            </div>

                            {isExpanded && project.carpetas && ( // Cambiado a 'carpetas' en lugar de 'folders'
                                <div className="folders">
                                    {project.carpetas.map((carpeta) => (
                                        <Folder key={carpeta.id} folder={{
                                            id: carpeta.id,
                                            name: carpeta.nombre, // Mapeamos el nombre para el componente Folder
                                            files: carpeta.archivos ? carpeta.archivos.map(archivo => ({
                                                id: archivo.id,
                                                name: archivo.nombre,
                                                content: archivo.contenido
                                            })) : []
                                        }} />
                                    ))}
                                </div>
                            )}
                        </div>
                    );
                })
            ) : (
                <div className="no-projects">
                    <p>No hay proyectos disponibles. Crea uno nuevo.</p>
                </div>
            )}
        </div>
    );
};

export default ProjectList;