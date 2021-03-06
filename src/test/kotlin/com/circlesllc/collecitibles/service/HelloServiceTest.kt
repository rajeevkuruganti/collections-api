package com.circlesllc.collecitibles.service


import com.circlesllc.collectibles.service.HelloService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class HelloServiceTest() {


    @Test
    fun `testing my first mockk`() {
//        @MockK
       val helloService: HelloService = mockk(relaxed = true)
        every{helloService.sayHello("rajeev")} returns "Hello rajeev"
        println(helloService.sayHello(("rajeev")))
        Assertions.assertEquals("Hello rajeev",helloService.sayHello(("rajeev")))
    }

    @Test
    fun `testing the real function`(){
//        @Autowired
        var helloService:HelloService = HelloService()
        println(helloService.sayHello("Inigo Montoya"))
        Assertions.assertEquals("Hello Mr Inigo Montoya", helloService.sayHello("Inigo Montoya"))

    }

}