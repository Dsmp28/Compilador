import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "./Button";
import carpeta from "../assets/carpeta.png";
import agregar from "../assets/agregar.png";
import editar from "../assets/editar.png";
import eliminar from "../assets/eliminar.png";
import expandir from "../assets/flecharriba.png";
import contraer from "../assets/flechabajo.png";
import File from "./File";

const Folder = ({ folder }) => {
    const navigate = useNavigate();
    const [isExpanded, setIsExpanded] = useState(false);
    
    const toggleFolder = () => {
        setIsExpanded(!isExpanded);
    };
    
    const crearArchivo = () => {
        console.log("Crear archivo en carpeta", folder.id);
    };
    
    const editarCarpeta = () => {
        console.log("Editar carpeta", folder.id);
    };
    
    const eliminarCarpeta = () => {
        console.log("Eliminar carpeta", folder.id);
    };

    const abrirArchivo = (file) => {
        navigate("/editor",{ state: { fileName : file.name, fileContent: file.content }});
    }

    return (
        <div className="folder-container">
            <div className="folder-header">
                <div className="folder-info">
                    <img src={carpeta} alt="Carpeta" className="folder-icon" />
                    <span className="folder-name">{folder.name}</span>
                </div>
                <div className="folder-actions">
                    <Button icon={agregar} title="Nuevo Archivo" onClick={crearArchivo} className="btn-icon">
                        <img src={agregar} alt="Nuevo Archivo" />
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
            {isExpanded && folder.files && folder.files.length > 0 && (
                <div className="files-container">
                    {folder.files.map((file) => (
                        <File key={file.id} file={file} onClick={abrirArchivo}/>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Folder;

