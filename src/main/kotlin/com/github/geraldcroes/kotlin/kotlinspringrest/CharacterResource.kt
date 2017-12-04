package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.CharacterNotFoundException
import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters/")
class CharacterResource(@Autowired val characterRepository: CharacterRepository) {
    @RequestMapping("")
    fun findAll() = characterRepository.find()

    @RequestMapping("{uid}")
    fun getById(@PathVariable("uid") uid: String) = characterRepository.get(uid)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CharacterNotFoundException::class)
    fun notFound(exception: CharacterNotFoundException) = object {
        val message = "Character ${exception.uid} not found"
    }
}