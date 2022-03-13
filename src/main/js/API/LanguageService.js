export default class LanguageService {
    //2 or in some cases 3-character code (en, ru, ua, es, ja, ...)
    static _languageCode;

    static nameByCode = {
        'en': 'English',
        'ru': 'Русский'
    }

    static autodetect() {
        let lang = 'en'
        const navLang = navigator.language || navigator.userLanguage
        if (navLang) {
            const twoCharsCode = navLang.substring(0, navLang.indexOf('-'))
            if (twoCharsCode in LanguageService.nameByCode) {
                lang = twoCharsCode
            }
        }
        console.log('Language autodetect: '+lang)
        this._languageCode = lang
    }

    static getLanguage() {
        return {
            code: this._languageCode,
            name: LanguageService.nameByCode[this._languageCode]
        }
    }

    static setLanguage(languageCode) {
        if (languageCode in LanguageService.nameByCode) {
            this._languageCode = languageCode
        } else {
            console.log('Language ' + languageCode + ' is not supported.')
        }
    }
}