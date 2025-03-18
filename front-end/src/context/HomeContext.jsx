// src/context/HomeContext.jsx
import "../styles/sidebar.css";
import React, { createContext, useState, useEffect, useMemo } from "react";
import axios from "axios";
import PropTypes from "prop-types";

// Configuración de la instancia de axios
const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL || "http://localhost:8080",
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true
});

export const HomeContext = createContext();

export const HomeProvider = ({ children }) => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Función para obtener la lista de proyectos
  const fetchProjects = async () => {
    try {
      setLoading(true);
      const response = await axiosInstance.get('/proyectos');
      setProjects(response.data);
      setError(null);
    } catch (err) {
      console.error("Error al obtener proyectos:", err);
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  // CRUD para Proyectos
  const createProject = async (projectName) => {
    try {
      const response = await axiosInstance.post('/proyectos', { nombre: projectName });
      setProjects(response.data);
      return response.data;
    } catch (err) {
      console.error("Error al crear proyecto:", err);
      setError(err);
      throw err;
    }
  };

  const updateProject = async (projectId, projectName) => {
    try {
      const response = await axiosInstance.put(`/proyectos/${projectId}`, { nombre: projectName });
      setProjects(response.data);
      return response.data;
    } catch (err) {
      console.error("Error al actualizar proyecto:", err);
      setError(err);
      throw err;
    }
  };

  const deleteProject = async (projectId) => {
    try {
      const response = await axiosInstance.delete(`/proyectos/${projectId}`);
      setProjects(response.data);
      return response.data;
    } catch (err) {
      console.error("Error al eliminar proyecto:", err);
      setError(err);
      throw err;
    }
  };

  // Operaciones para carpetas
  const createFolder = async (projectId, folderName) => {
    try {
      const information = {
        nombre: folderName,
        proyecto: {
          id: projectId
        },
        archivos: []
      };
      const response = await axiosInstance.post(`/carpetas`, information);
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al crear carpeta:", err);
      setError(err);
      throw err;
    }
  };

  const updateFolder = async (folderId, folderName) => {
    try {
      const response = await axiosInstance.put(`/carpetas/${folderId}`, { nombre: folderName });
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al actualizar carpeta:", err);
      setError(err);
      throw err;
    }
  };

  const deleteFolder = async (folderId) => {
    try {
      const response = await axiosInstance.delete(`/carpetas/${folderId}`);
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al eliminar carpeta:", err);
      setError(err);
      throw err;
    }
  };

  // Operaciones para archivos
  const createFile = async (folderId, fileName, fileContent) => {
    try {
      const information = {
        nombre: fileName,
        contenido: fileContent,
        carpeta: {
          id: folderId
        }
      };
      const response = await axiosInstance.post(`archivos`, information);
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al crear archivo:", err);
      setError(err);
      throw err;
    }
  };

  const updateFile = async (projectId, folderId, fileId, fileName, fileContent) => {
    try {
      const response = await axiosInstance.put(`/proyectos/${projectId}/carpetas/${folderId}/archivos/${fileId}`, { 
        nombre: fileName,
        contenido: fileContent
      });
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al actualizar archivo:", err);
      setError(err);
      throw err;
    }
  };

  const deleteFile = async (projectId, folderId, fileId) => {
    try {
      const response = await axiosInstance.delete(`/proyectos/${projectId}/carpetas/${folderId}/archivos/${fileId}`);
      fetchProjects(); // Recargar todos los proyectos
      return response.data;
    } catch (err) {
      console.error("Error al eliminar archivo:", err);
      setError(err);
      throw err;
    }
  };

  // Se memoiza el valor del contexto para evitar re-renderizados innecesarios
  const contextValue = useMemo(
    () => ({
      projects,
      loading,
      error,
      fetchProjects,
      createProject,
      updateProject,
      deleteProject,
      createFolder,
      updateFolder,
      deleteFolder,
      createFile,
      updateFile,
      deleteFile
    }),
    [projects, loading, error]
  );

  // Cargar proyectos al montar el provider
  useEffect(() => {
    fetchProjects();
  }, []);

  return (
    <HomeContext.Provider value={contextValue}>
      {children}
    </HomeContext.Provider>
  );
};

HomeProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export default HomeContext;