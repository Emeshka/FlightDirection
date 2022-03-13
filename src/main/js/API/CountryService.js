import axios from 'axios';

export default class CountryService {
    static host = '';

    static async getAll(languageCode) {
        const url = CountryService.host + '/api/countries/' + languageCode
        const response = await axios.get(url)
        return response.data
    }

    static async create(nameOriginal) {
        const url = CountryService.host + '/api/country'
        const bodyFormData = new FormData()
        bodyFormData.append('nameOriginal', nameOriginal)
        const response = await axios.post(url, bodyFormData, {
            headers: {
              "Content-Type": "multipart/form-data"
            }
        })
        return response.data
    }
}