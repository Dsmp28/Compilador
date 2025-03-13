import React from "react";
import styled from "styled-components";
import Button from "../../components/Button.jsx";

const StyledTopBar = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0px 20px;
    background: white;
    border-bottom: 2px solid #ddd;
    height:46px;
`;


const TopBar = ({onSave}) => {
    return (
        <StyledTopBar>
            <Button text="Guardar" onClick={onSave} className="btnBar" />
        </StyledTopBar>
    );
};

export default TopBar;
