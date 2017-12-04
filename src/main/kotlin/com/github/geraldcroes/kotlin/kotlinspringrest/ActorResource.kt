package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.ActorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/actors/")
class ActorResource(@Autowired val actorRepository : ActorRepository) {
    @RequestMapping("")
    fun findAll() = actorRepository.find()

    @RequestMapping("{uid}")
    fun getById(@PathVariable("uid") uid : String) = actorRepository.get(uid)
}