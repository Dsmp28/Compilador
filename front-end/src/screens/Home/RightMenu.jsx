import React, {useState} from 'react';
import styled from 'styled-components';
import ProjectList from "../../components/ProjectList.jsx";
const StyledRightComponent = styled.div`
    width: 55vw;
    background-color: #fafafa;
    padding: 20px;
    overflow-y:auto;
    height:100vh;
`
const RightMenu = () => {
    const [projects, setProjects] = useState([ // Aca deberia de recibir la lista
        {
            id: 1,
            name: "Project 1",
            folders: [
                {
                    id: 1,
                    name: "Folder 1",
                    files: [
                        { id: 1, name: "Archivo1"},
                        { id: 2, name: "Archivo2"}
                    ]
                },
                {
                    id: 2,
                    name: "Folder 2",
                    files: [
                    ]
                }
            ]
        },
        {
            id: 2,
            name: "Project 2",
            folders: [
                {
                    id: 3,
                    name: "Folder 3",
                    files: [
                    ]
                },
                {
                    id: 4,
                    name: "Folder 4",
                    files: [
                    ]
                }
            ]
        }
    ]);
    return (
        <StyledRightComponent>
        <div className="right-menu">
            <h2 className={"tituloProyectos"}>Tus proyectos</h2>
            <ProjectList projects={projects} /> {/* Se le pasa la lista de proyectos */}
        </div>
        </StyledRightComponent>
    );
}
export default RightMenu;