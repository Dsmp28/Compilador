import React from "react";
import Tree from "react-d3-tree";

const TreeVisualizer = ({ data }) => {
    const containerStyles = {
        width: "100%",
        height: "500px",
    };

    return (
        <div style={containerStyles}>
            <Tree data={[data]} orientation="vertical" />
        </div>
    );
};

export default TreeVisualizer;