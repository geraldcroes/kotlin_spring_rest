package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet

data class Actor(
        val uid: String,
        val name: String,
        val actingHistory: List<Acting>
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
        return jdbcTemplate.queryForList(BASE_QUERY, MapSqlParameterSource()).groupBy {
            it.get("UID")
        }.map {
            Actor ("Toto", "Titi",
                    it.value.map {
                        Acting(it.get("MOVIE_UID") as String, it.get("CHARACTER_UID") as String)
                    }
            )
        }
    }

    fun get(uid: String) =
            jdbcTemplate.queryForObject(
                    "$BASE_QUERY where uid = :uid",
                    MapSqlParameterSource("uid", uid),
                    this::mapRow
            )

    fun mapRow(resultSet: ResultSet, rowNum: Int) =
            Character(
                    resultSet.getString("uid"),
                    resultSet.getString("name")
            )
}

@RestController
@RequestMapping("/actors/")
class ActorController(@Autowired val actorRepository: ActorRepository) {
    @GetMapping("")
    fun find() = actorRepository.find()
}