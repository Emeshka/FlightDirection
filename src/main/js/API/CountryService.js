import axios from 'axios';

export default class CountryService {
    static async getAll() {
        const response = await axios.get('localhost:8080/countries')
        return response.data
    }

    /*static async create(country) {
        const response = await axios.post('localhost:8080/country')
        return response.data
    }*/
}