import axios from 'axios';

export default class FileBlobService {
    static async getAll() {
        const response = await axios.get('localhost:8080/files')
    }
}