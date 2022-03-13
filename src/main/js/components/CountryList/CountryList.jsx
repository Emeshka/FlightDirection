import React from "react";

const CountryList = ({countries}) => {
    return (
        <div>
            {countries.map(c => c.name).join(', ')}
        </div>
    );
}

export default CountryList;