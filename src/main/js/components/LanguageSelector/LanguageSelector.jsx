import React from "react";
import Select from 'react-select';
import LanguageService from "../../API/LanguageService";

const LanguageSelector = ({onChange}) => {
    const options = []
    let selectedOption = null
    for (let code in LanguageService.nameByCode) {
        const newOption = {
            value: code,
            label: LanguageService.nameByCode[code]
        }
        if (LanguageService.getLanguage().code == code) {
            selectedOption = newOption
        }
        options.push(newOption)
    }
    return (
        <Select
            defaultValue={selectedOption}
            onChange={onChange}
            options={options}
        />
    );
}

export default LanguageSelector;