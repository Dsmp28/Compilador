import carpeta from '../assets/carpeta.png';
import File from './File';
import Button from "./Button.jsx";
import agregar from "../assets/agregar.png";
import editar from "../assets/editar.png";
import eliminar from "../assets/eliminar.png";
import expandir from "../assets/flecharriba.png";
import contraer from "../assets/flechabajo.png";
import { useState } from "react";
import importar from "../assets/importar.png";
import { useNavigate } from "react-router-dom";

const Folder = ({ folder }) => {
    const navigate = useNavigate();
    const [isExpanded, setIsExpanded] = useState(false);

    const toggleFolder = (folderId) => {
        setIsExpanded((prev) => !prev);
    };

    const importarArchivo = () => {
        console.log("Importar Archivo"); // Lógica para importar un archivo
    };

    const editarCarpeta = () => {
        console.log("Editar proyecto"); // Lógica para editar un proyecto
    };

    const eliminarCarpeta = () => {
        console.log("Eliminar proyecto"); // Lógica para eliminar un proyecto
    };
    const abrirArchivo = (file) => {
        navigate("/editor",{ state: { fileName : file.name, fileContent: file.content }});
    }

    const files = [ //Acá se deberían de inicializar los archivos de cada carpeta.
        {
            id: 1,
            name: "Archivo 1",
            content: "Contenido del archivo 1",
        },
        {
            id: 2,
            name: "Archivo 2",
            content: "Contenido del archivo 2",
        }
    ]

    return (
                <div className="folder-container">
                    <div className="folder-header">
                        <div className="folder-info">
                            <img src={carpeta} alt="Carpeta" className="folder-icon" />
                            <span className="folder-name">{folder.name}</span>
                        </div>
                        <div className="folder-actions">
                            <Button icon={importar} title="Importar archivo" onClick={importarArchivo} className="btn-icon">
                                <img src={importar} alt="Importar archivo" />
                            </Button>
                            <Button icon={editar} title="Editar" onClick={editarCarpeta} className="btn-icon">
                                <img src={editar} alt="Editar" />
                            </Button>
                            <Button icon={eliminar} title="Eliminar" onClick={eliminarCarpeta} className="btn-icon">
                                <img src={eliminar} alt="Eliminar" />
                            </Button>
                            <Button
                                icon={isExpanded ? expandir : contraer}
                                title={isExpanded ? "Contraer" : "Expandir"}
                                onClick={toggleFolder}
                                className="btn-icon"
                            >
                                <img src={isExpanded ? expandir : contraer} alt="Expandir/Contraer" />
                            </Button>
                        </div>
                    </div>
                    {isExpanded && (
                        <div className="files-container">
                            {folder.files && folder.files.length > 0 ? (
                                folder.files.map((file) => <File key={file.id} file={file} onClick={abrirArchivo}/>)
                            ) : (
                                <p>No hay archivos en esta carpeta.</p>
                            )}
                        </div>
                    )}
                </div>
            );
};

export default Folder;
