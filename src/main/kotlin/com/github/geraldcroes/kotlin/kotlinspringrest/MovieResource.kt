package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies/")
class MovieResource(@Autowired val movieRepository: MovieRepository) {
    @RequestMapping("")
    fun findAll() = movieRepository.find()

    @RequestMapping("{uid}")
    fun getById(@PathVariable("uid") uid: String) = movieRepository.get(uid)
}