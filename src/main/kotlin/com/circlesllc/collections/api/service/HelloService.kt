package com.circlesllc.collections.api.service

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun sayHello(name: String): String{
        return "Hello Mr $name"
    }

}
