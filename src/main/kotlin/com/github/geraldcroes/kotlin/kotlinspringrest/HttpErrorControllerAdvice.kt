package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.MovieNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class HttpErrorControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException::class)
    @ResponseBody
    fun movieNotFound(e: MovieNotFoundException) = object {
        val message: String = "Movie ${e.uid} not found"
    }
}