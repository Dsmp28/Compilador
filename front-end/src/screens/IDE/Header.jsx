import React from "react";
import styled from "styled-components";
import codigoImagen from "../../assets/codigo.png";

const StyledHeader = styled.div`
  width: 100%;
  height: 60px;
  background-color: #D9D9D9;
    justify-content: center;
  display: flex;
  align-items: center;
  color: black;
  font-size: 1.6rem;
  font-weight: bold;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
`

const Header = () => {
    return (
        <StyledHeader>
            <img src={codigoImagen} alt="Codigo" style={{width: "55px", height: "55px"}}/>
            <span style={{marginLeft: "10px"}}>CompileX</span>
        </StyledHeader>
    )
};

export default Header;
