import documento from "../assets/measurement.png";
const File = ({ file, onClick}) => {
    return (
        <div className="file-card" onClick={onClick}>
            <img src={documento} className="file-icon" />
            <span className="file-name">{file.name}</span>
        </div>
    );
};

export default File;
