package com.example.demo

import groovy.util.logging.Slf4j

@Slf4j
class LogTestWithStringTemplate {

    void test() {
        def a = "A"
        log.info("Headers ${a}")
    }
}
