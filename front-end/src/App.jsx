// App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./screens/Home";
import IDE from "./screens/IDE";
import { HomeProvider } from "./context/HomeContext";
import { IdeProvider } from "./context/IdeContext";

function App() {
  return (
    <HomeProvider>
      <IdeProvider>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/editor" element={<IDE />} />
        </Routes>
      </IdeProvider>
    </HomeProvider>
  );
}

export default App;

