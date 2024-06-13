package com.c6.student

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@EnableFeignClients
@SpringBootApplication
class StudentApplication

fun main(args: Array<String>) {
	runApplication<StudentApplication>(*args)
}
