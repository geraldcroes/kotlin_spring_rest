package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinSpringRestApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinSpringRestApplication::class.java, *args)
}
