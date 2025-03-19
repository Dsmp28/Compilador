import React from "react";
import styled from "styled-components";
import CodeEditor from "./CodeEditor.jsx";
import OutputConsole from "./OutputConsole.jsx";

const StyledMainContainer = styled.div`
  display: flex;
  width: 100%;
  height: calc(100vh - 60px);
`;

const MainContainer = ({fileContent, fileName}) => {
    return (
        <StyledMainContainer>
            <CodeEditor initialContent={fileContent} fileName={fileName}/>
            <OutputConsole />
        </StyledMainContainer>
    );
};

export default MainContainer;
