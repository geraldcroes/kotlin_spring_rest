package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Actor(
        val uid: String,
        val name: String,
        val actings: List<Acting>
)

data class Acting(
        val movieUid: String,
        val characterUid: String
)

@Repository
open class ActorRepository(@Autowired val jdbcTemplate: NamedParameterJdbcOperations) {
    val BASE_QUERY = """select uid, name, movie_uid, character_uid
                        from actors a
                        left JOIN actings on uid = actor_uid
                        """

    fun find(): List<Actor> {
        return map(jdbcTemplate.queryForList(BASE_QUERY, MapSqlParameterSource()))
    }

    fun get(uid: String) = try {
        map(
                jdbcTemplate.queryForList(
                        "$BASE_QUERY where a.uid = :uid",
                        MapSqlParameterSource("uid", uid))
        ).first()
    } catch (exception: NoSuchElementException) {
        throw ActorNotFoundException(uid);
    }


    fun map(results: List<Map<String, Any>>) = results.groupBy {
        it.get("UID")
    }.map {
        Actor(
                it.value.first().get("UID") as String,
                it.value.first().get("NAME") as String,
                it.value.filter {
                    it.get("MOVIE_UID") != null
                }.map {
                        Acting(
                                it.get("MOVIE_UID") as String,
                                it.get("CHARACTER_UID") as String
                        )
                }
        )
    }
}

class ActorNotFoundException(val uid: String) : Exception()

@RestController
@RequestMapping("/actors/")
class ActorController(@Autowired val actorRepository: ActorRepository) {
    @GetMapping("")
    fun find() = actorRepository.find()

    @GetMapping("/{uid}")
    fun get(@PathVariable("uid") uid: String) = actorRepository.get(uid)
}