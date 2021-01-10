package com.fourcircles.learning.mock.service;

import com.fourcircles.learn.mock.service.HelloService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HelloServiceTest {
    @Mock
    HelloService helloServiceMock;

    @Test
    void test1(){
      when(helloServiceMock.sayHello("world")).thenReturn("Hello World!");
      System.out.println(helloServiceMock.sayHello("world"));

//      assertEquals("Hello World", helloServiceMock.sayHello("world") );
    }
}
