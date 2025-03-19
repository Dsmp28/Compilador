// src/context/IdeContext.jsx
import React, { createContext, useState, useMemo } from 'react';
import axios from 'axios';
import PropTypes from 'prop-types';

export const IdeContext = createContext();

// Configuración de la instancia de axios
const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL || 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

export const IdeProvider = ({ children }) => {
  const [codeResponse, setCodeResponse] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Función para enviar el código al backend y obtener la respuesta
  const compileCode = async (codigo) => {
    setLoading(true);
    setError(null);
    try {
      // Se envía el objeto con la propiedad "codigo" (según CodeRequestDTO)
      const information = {
        codigo: codigo
      };
      const response = await axiosInstance.post('/compilador', information);
      setCodeResponse(response.data); // Se asume que la respuesta es un objeto conforme a CodeResponseDTO
      return response.data;
    } catch (err) {
      console.error('Error al compilar el código:', err);
      setError(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const resetOutput = () => {
    setCodeResponse(null);
  };

  const handleExport = async (code, fileName) => {
    const suggestedName = fileName ? fileName + ".txt" : "archivo.txt";

    if (window.showSaveFilePicker) {

      try {
        const options = {
          suggestedName,
          types: [
            {
              description: "Archivo de texto",
              accept: { "text/plain": [".txt"] },
            },
          ],
        };

        const fileHandle = await window.showSaveFilePicker(options);

        const writable = await fileHandle.createWritable();
        await writable.write(code);
        await writable.close();

        alert("Archivo guardado exitosamente.");
      } catch (err) {
        console.error("Error al guardar archivo:", err);
        alert("No se pudo guardar el archivo o se canceló la operación.");
      }
    } else {
      const blob = new Blob([code], { type: "text/plain;charset=utf-8" });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = suggestedName;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);

      alert("Archivo descargado exitosamente (método fallback).");
    }
  };



  // Se memoiza el valor del contexto para evitar re-renderizados innecesarios
  const contextValue = useMemo(() => ({
    codeResponse,
    loading,
    error,
    compileCode,
    resetOutput,
    handleExport,
  }), [codeResponse, loading, error]);

  return (
    <IdeContext.Provider value={contextValue}>
      {children}
    </IdeContext.Provider>
  );
};

IdeProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export default IdeContext;
