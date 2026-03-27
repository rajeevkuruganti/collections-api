package com.circlesllc.collections.api.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@RestController("/")
@CrossOrigin("*")
//@RequestMapping("/")
class AccessController {

    @GetMapping
    fun showHome(): ModelAndView {
        var mav: ModelAndView? = null
        mav = ModelAndView("login")
        mav.addObject("login.html")
        return mav
    }

}