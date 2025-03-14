import React from "react";
import styled from "styled-components";
import Button from "../../components/Button.jsx";
import exportar from "../../assets/exportar.png";

const StyledBottomBar = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background: white;
    border-top: 2px solid #ddd;
`;
const BottomBar = ({onExport, onExecute}) => {
    return (
        <StyledBottomBar>
            <div className={"btnExportar"}>
                <Button
                    onClick={onExport}
                    icon={exportar}
                    className = {"btn-icon"}
                />
                <span style={{marginLeft: "10px", }}>Exportar</span>
            </div>
            <Button
                text="Ejecutar código"
                onClick={onExecute}
                className={"btnBar"}
            />
        </StyledBottomBar>
    );
};

export default BottomBar;
