package com.github.vbauer.yta.service.fraction

import com.github.vbauer.yta.common.AbstractApiSpec
import com.github.vbauer.yta.model.Language

import static com.github.vbauer.yta.service.fraction.impl.LanguageApiImpl.ATTR_UI
import static com.github.vbauer.yta.service.fraction.impl.LanguageApiImpl.METHOD_GET_LANGS

/**
 * Tests for {@link LanguageApi}.
 *
 * @author Vladislav Bauer
 */

class LanguageApiSpec extends AbstractApiSpec {

    def "Check Language API"() {
        setup:
            def langApi = api.languageApi()
        expect:
            langApi != null

        when: "UI parameter is not passed"
            def allLanguages = langApi.all()
        then:
            !allLanguages.toString().empty
            !allLanguages.directions().empty
            !allLanguages.languages().empty

        when: "UI parameter is an object"
            def allLanguagesEn = langApi.all(Language.EN)
        then:
            def langs = allLanguagesEn.languages()
            def dirs = allLanguagesEn.directions()

            !dirs.empty
            !langs.empty

            langs.each { l ->
                def fieldName = l.code().toString().toUpperCase()
                def field = Language.class.getField(fieldName)
                assert field != null

                def lang = field.get(null)
                assert lang == l
            }

        when: "UI parameter is a string"
            def allLanguagesRu = langApi.all("ru")
        then:
            !allLanguagesRu.directions().empty
            !allLanguagesRu.languages().empty
    }

    def "Check constants"() {
        expect:
            constant == value
        where:
            constant         | value
            METHOD_GET_LANGS | "/getLangs"
            ATTR_UI          | "ui"
    }

}
