package com.github.geraldcroes.kotlin.kotlinspringrest

import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.ActorNotFoundException
import com.github.geraldcroes.kotlin.kotlinspringrest.repositories.ActorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/actors/")
class ActorResource(@Autowired val actorRepository: ActorRepository) {
    @RequestMapping("")
    fun findAll() = actorRepository.find()

    @RequestMapping("{uid}")
    fun getById(
            @PathVariable("uid") uid: String,
            response: HttpServletResponse
    ) = try {
        actorRepository.get(uid)
    } catch (notFoundException: ActorNotFoundException) {
        response.status = NOT_FOUND.value()
        object {
            val error: String = "Actor ${notFoundException.uid} not found."
        }
    }
}