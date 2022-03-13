import React from "react";

const CountryList = ({countries}) => {
    return (
        <div>
            CountryList component
            {countries.map(c => c.name).join(', ')}
        </div>
    );
}

export default CountryList;