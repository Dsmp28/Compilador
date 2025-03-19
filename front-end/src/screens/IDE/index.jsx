import {useLocation} from "react-router-dom";
import styled from "styled-components";
import Header from "./Header.jsx";
import MainContainer from "./MainContainer.jsx";


const StyledIDE = styled.div`
    width: 100vw;
    min-height: 100vh;
    display: flex;
    overflow:hidden;
    flex-direction: column;
`
const IDE = () => {
    const location = useLocation();
    const { fileName, fileContent, fileId } = location.state || { fileName: "Sin Nombre", fileContent: "No hay contenido" };
    return (
        <StyledIDE>
            <Header />
            <MainContainer fileContent={fileContent} fileName={fileName} fileId={fileId}/>
        </StyledIDE>
    );
}
export default IDE;