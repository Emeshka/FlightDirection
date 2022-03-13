import React, { useEffect, useState } from "react";
import ReactDOM from "react-dom";
import LanguageService from "./API/LanguageService";
import CountryService from "./API/CountryService";
import CountryList from "./components/CountryList/CountryList.jsx";
import LanguageSelector from "./components/LanguageSelector/LanguageSelector.jsx";
import FileBlobService from "./API/FileBlobService";

function App() {
    LanguageService.autodetect();
    const apiServices = [CountryService, FileBlobService]
    apiServices.forEach(service => service.host = window.location.protocol + '//' + window.location.host)

    let [countries, setCountries] = useState([])
    let [newCountryNameOriginal, setNewCountryNameOriginal] = useState('')

    /*useEffect(() => {<CountryList countries={countries}></CountryList>
        const countriesList = await CountryService.getAll()
        setCountries(countriesList)
    }, [])*/
    
    function globalLanguageChange({value}) {
        LanguageService.setLanguage(value)
        console.log(LanguageService.getLanguage())
    }

    async function createCountry() {
        console.log(newCountryNameOriginal)
        try {
            console.log(await CountryService.create(newCountryNameOriginal))
        } catch (e) {
            console.log(e)
        }
    }

    return (
        <div>
            <LanguageSelector onChange={globalLanguageChange}/>
            <h1>Welcome to React Front End Served by Spring Boot</h1>
            <h1>7d7d7d7d7d7d7d7d7</h1>
            <input
                defaultValue={newCountryNameOriginal}
                onInput={() => setNewCountryNameOriginal(event.target.value)}
            />
            <input
                type="button"
                onClick={createCountry}
                value="Create country"
            />
            <CountryList countries={countries}></CountryList>
        </div>
    );
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));