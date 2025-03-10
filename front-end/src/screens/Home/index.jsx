import React from "react"
import styled from 'styled-components'
import LeftMenu from './LeftMenu.jsx'
import RightMenu from './RightMenu.jsx'

const StyledHome = styled.div`
    width: 100vw;
    min-height: 100vh;
    display: flex;
    overflow:hidden;
`

const Home = () => {
    return(
        <StyledHome>
            <LeftMenu />
            <RightMenu />
        </StyledHome>
    )
}
export default Home;