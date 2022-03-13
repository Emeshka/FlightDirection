import axios from 'axios';

export default class FileBlobService {
    static host = '';

    static async getAll() {
        const url = FileBlobService.host + '/api/files'
        const response = await axios.get(url)
        return response.data
    }

    static async getById(id) {
        const url = FileBlobService.host + '/api/file/' + id
        const response = await axios.get(url)
        return response.data
    }

    static async create(fileData) {
        const url = FileBlobService.host + '/api/upload'
        const response = await axios.post(url, {
            file: fileData
        }, {
            headers: {
              "Content-Type": "multipart/form-data"
            }
        })
        return response.data
    }
}