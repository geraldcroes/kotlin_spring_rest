package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/characters/")
class CharacterResource(@Autowired val characterRepository: CharacterRepository) {
    @RequestMapping("")
    fun findAll() = characterRepository.find()

    @RequestMapping("{uid}")
    fun getById(@PathVariable("uid") uid : String) = characterRepository.get(uid)
}