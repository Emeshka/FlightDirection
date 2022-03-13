import React from "react";

const LanguageSelector = ({options, onchange}) => {
    return (
        <select>
            {options.map(c => c.name).join(', ')}
        </select>
    );
}

export default LanguageSelector;