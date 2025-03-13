import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes, useNavigate, useLocation } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './screens/Home'
import IDE from './screens/IDE'

function App() {
  return (
        <>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/editor" element={<IDE />} />
            </Routes>
        </>
  );
}

export default App
