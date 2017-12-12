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
class ActorRepository(@Autowired val jdbcTemplate: NamedParameterJdbcOperations) {
    val BASE_QUERY = """select a.uid, a.name, act.movie_uid, act.character_uid
                        from actors a, actings act
                        where a.uid=act.actor_uid"""

    fun find() : List<Actor> {
        return map(jdbcTemplate.queryForList(BASE_QUERY, MapSqlParameterSource()))
    }

    fun get(uid: String) =  map(
        jdbcTemplate.queryForList(
        "$BASE_QUERY AND a.uid = :uid",
        MapSqlParameterSource("uid", uid))
    ).first()

    fun map(results: List<Map<String, Any>>) = results.groupBy {
        it.get("UID")
    }.map {
        Actor (
                it.value.first().get("UID") as String,
                it.value.first().get("NAME") as String,
                it.value.map {
                    Acting(
                            it.get("MOVIE_UID") as String,
                            it.get("CHARACTER_UID") as String
                    )
                }
        )
    }
}

@RestController
@RequestMapping("/actors/")
class ActorController(@Autowired val actorRepository: ActorRepository) {
    @GetMapping("")
    fun find() = actorRepository.find()

    @GetMapping("/{uid}")
    fun get(@PathVariable("uid") uid: String) = actorRepository.get(uid)
}