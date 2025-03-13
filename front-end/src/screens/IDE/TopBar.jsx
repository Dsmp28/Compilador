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

const Select = styled.select`
    padding: 5px;
    border-radius: 5px;
    font-size: 14px;
    background:white;
    color: black;
    border-color: #ddd;
`;

const TopBar = ({ onSave, onThemeChange }) => {
    return (
        <StyledTopBar>
            <Button text="Guardar" onClick={onSave} className="btnBar" />
            <Select onChange={(e) => onThemeChange(e.target.value)}>
                <option value="dark">Oscuro</option>
                <option value="light">Claro</option>
            </Select>
        </StyledTopBar>
    );
};

export default TopBar;
