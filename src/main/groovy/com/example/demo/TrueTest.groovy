package com.example.demo

import spock.lang.Specification

class TrueTest extends Specification {

    def 'true test'() {
        when:
        def a = true

        then:
        a
    }

}
