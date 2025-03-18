import React, { useContext } from 'react';
import styled from 'styled-components';
import ProjectList from "../../components/ProjectList.jsx";
import HomeContext from '../../context/HomeContext.jsx'; // Importamos el contexto

const StyledRightComponent = styled.div`
    width: 55vw;
    background-color: #fafafa;
    padding: 20px;
    overflow-y: auto;
    height: 100vh;
`;

const LoadingMessage = styled.div`
    text-align: center;
    padding: 20px;
    font-size: 16px;
    color: #666;
`;

const ErrorMessage = styled.div`
    text-align: center;
    padding: 20px;
    font-size: 16px;
    color: #ff4444;
    background-color: #ffeeee;
    border-radius: 5px;
    margin-bottom: 20px;
`;

const RightMenu = () => {
    // Usamos el contexto para obtener los proyectos, estado de carga y errores
    const { projects, loading, error, fetchProjects } = useContext(HomeContext);

    // Función para reintentar la carga si hay un error
    const handleRetry = () => {
        fetchProjects();
    };

    return (
        <StyledRightComponent>
            <div className="right-menu">
                <h2 className="tituloProyectos">Tus proyectos</h2>
                
                {/* Mostrar mensaje de carga si está cargando */}
                {loading && <LoadingMessage>Cargando proyectos...</LoadingMessage>}
                
                {/* Mostrar mensaje de error si hay un error */}
                {error && (
                    <ErrorMessage>
                        Error al cargar los proyectos. 
                        <button 
                            onClick={handleRetry}
                            style={{ marginLeft: '10px', padding: '5px 10px', cursor: 'pointer' }}
                        >
                            Reintentar
                        </button>
                    </ErrorMessage>
                )}
                
                {/* Mostrar la lista de proyectos cuando no hay error y no está cargando */}
                {!loading && !error && <ProjectList projects={projects} />}
            </div>
        </StyledRightComponent>
    );
};

export default RightMenu;