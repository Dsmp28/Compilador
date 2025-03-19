import documento from "../assets/measurement.png";
import {useRef, useState, useEffect} from "react";
import Button from "./Button.jsx";
import guardar from "../assets/guardar.png";
import cancelar from "../assets/cancelar.png";
const File = ({ file, onClick}) => {
    const [showOptions, setShowOptions] = useState(false);
    const [renaming, setRenaming] = useState(false);
    const [newName, setNewName] = useState(file.name);
    const containerRef = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (containerRef.current && !containerRef.current.contains(event.target)) {
                setShowOptions(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);

        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    const handleContextMenu = (e) => {
        e.preventDefault();
        setShowOptions(true);
    };

    const handleRename = (e) => {
        e.stopPropagation();
        setRenaming(true);
        setShowOptions(false);
    };

    const confirmRename = () => {
        console.log("Nuevo nombre:", newName);
        // Lógica para renombrar el archivo
        setRenaming(false);
    };

    const handleDelete = (e) => {
        e.stopPropagation();
        if (window.confirm("¿Estás seguro de que deseas eliminar este archivo?")) {
            try {
                //Lógica para eliminar un archivo
            } catch (error) {
                console.error("Error al eliminar archivo:", error);
            }
        }
        setShowOptions(false);
    };
    return (
        <div
            className="file-card bg-gray-200 rounded-lg p-4 flex flex-col items-center shadow"
            onClick={(e) => {
                // Si está mostrando el menú contextual, no va a navegar
                if (!showOptions) onClick(file);
            }}
            onContextMenu={handleContextMenu}
            ref={containerRef}
        >
            <img src={documento} className="file-icon"  alt="icono" />
            <span className="file-name">{file.name}</span>

            {showOptions && (
                <div className="flex gap-6 mt-4">
                    <Button className={"new-project-btn"} text={"Renombrar"} style={{marginRight: "5px"}} onClick={(e) => handleRename(e)}></Button>
                    <Button className={"new-project-btn"} text={"Eliminar"} style={{marginLeft: "5px"}} onClick={(e) => handleDelete(e)}></Button>
                </div>
            )}
            {renaming && (
                <div className="absolute bg-white p-4 rounded shadow top-full mt-2 w-64 z-50" onClick={(e) => e.stopPropagation()}>
                    <input
                        onClick={(e) => e.stopPropagation()}
                        type="text"
                        value={newName}
                        onChange={(e) => setNewName(e.target.value)}
                        autoFocus
                        style={{ marginRight: "8px",
                            backgroundColor: "#ffffffde",
                            color: "#000000",}}
                    />
                    <div className="gap-6 mt-4">
                        <Button className="btn-smalledit" icon={cancelar} onClick={(e) => {
                            e.stopPropagation();
                            setRenaming(false);
                        }}>Cancelar</Button>
                        <Button className="btn-smalledit" icon={guardar} onClick={(e) => {
                            e.stopPropagation();
                            confirmRename(); }}></Button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default File;
