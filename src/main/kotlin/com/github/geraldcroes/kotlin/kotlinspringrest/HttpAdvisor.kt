package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ControllerAdvice
class HttpAdvisor {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException::class)
    @ResponseBody
    fun movieNotFound(e: MovieNotFoundException) = object {
        val message: String = "Movie ${e.uid} not found"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CharacterNotFoundException::class)
    @ResponseBody
    fun characterNotFound(exception: CharacterNotFoundException) = object {
        val message = "Character ${exception.uid} not found"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ActorNotFoundException::class)
    @ResponseBody
    fun characterNotFound(exception: ActorNotFoundException) = object {
        val message = "Actor ${exception.uid} not found"
    }
}
