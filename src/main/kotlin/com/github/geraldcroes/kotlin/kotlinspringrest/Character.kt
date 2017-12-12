package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet

data class Character(
        val uid: String,
        val name: String
)

@Repository
class CharacterRepository(@Autowired val jdbcTemplate: NamedParameterJdbcOperations) {
    val BASE_QUERY = "select uid, name from characters"

    fun find() = jdbcTemplate.query(BASE_QUERY, this::mapRow)

    fun get(uid: String) = try {
        jdbcTemplate.queryForObject(
                "$BASE_QUERY where uid = :uid",
                MapSqlParameterSource("uid", uid),
                this::mapRow
        )
    } catch (exception: EmptyResultDataAccessException) {
        throw CharacterNotFoundException(uid)
    }

    fun mapRow(resultSet: ResultSet, rowNum: Int) =
            Character(
                    resultSet.getString("uid"),
                    resultSet.getString("name")
            )
}

class CharacterNotFoundException(val uid: String) : Exception()

@RestController
@RequestMapping("/characters/")
class CharacterController(@Autowired val characterRepository: CharacterRepository) {
    @GetMapping("")
    fun findCharacters() = characterRepository.find()

    @GetMapping("{uid}")
    fun getCharacterByUid(@PathVariable("uid") uid: String) = characterRepository.get(uid)
}