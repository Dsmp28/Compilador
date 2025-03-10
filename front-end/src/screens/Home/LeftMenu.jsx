import React from 'react';
import styled from 'styled-components';
import Sidebar from '../../components/Sidebar.jsx';

const StyledLeftComponent = styled.div`
    width: 45vw;
    height: 100vh;
    background-color: #1e1e1e;

    display: flex;
    justify-content: center;
    align-items: center;
    
`

const LeftMenu = () => {
    return (
        <StyledLeftComponent>
        <div className="left-menu">
            <Sidebar />
        </div>
        </StyledLeftComponent>
    );
}
export default LeftMenu;