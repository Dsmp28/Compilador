const ProjectModal = ({ isOpen, onClose, onCreate }) => {
    if(!isOpen) return null;

    return (
        <div className="modal">
            <h2 className={"textoNuevoProyecto"}>Nuevo Proyecto</h2>
            <input className={"textFieldProyecto"} type="text" placeholder="Nombre del proyecto" />
            <div className="modal-buttons">
                <button onClick={onCreate} className={"nuevoConfirmacion"}>Crear</button>
                <button onClick={onClose} className={"nuevoConfirmacion"}>Cerrar</button>
            </div>
        </div>
    );
}

export default ProjectModal;