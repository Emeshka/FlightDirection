export default class LanguageService {
    //2 or in some cases 3-character code (en, ru, ua, es, ja, ...)
    static _languageCode;

    static nameByCode = {
        'en': 'English',
        'ru': 'Русский'
    }

    static autodetect() {
        this._languageCode = navigator.language || navigator.userLanguage || 'en'; 
    }

    static getLanguage() {
        return {
            code: this._languageCode,
            name: this.nameByCode[this._languageCode]
        }
    }

    static setLanguage(languageCode) {
        if (languageCode in nameByCode) {
            this._languageCode = languageCode
        } else {
            console.log('Language ' + languageCode + ' is not supported.')
        }
    }
}