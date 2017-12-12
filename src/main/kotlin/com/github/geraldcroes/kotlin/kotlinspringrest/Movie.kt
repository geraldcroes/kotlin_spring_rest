package com.github.geraldcroes.kotlin.kotlinspringrest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.bind.annotation.GetMapping
import java.sql.ResultSet

data class Movie(
        val uid: String,
        var title: String,
        var year: Number
)

@Repository
class MovieRepository(@Autowired val jdbcTemplate: NamedParameterJdbcOperations) {
    val BASE_QUERY = "select uid, title, year from movies"

    fun find() = jdbcTemplate.query(BASE_QUERY, this::mapRow)

    fun get(uid: String) =
        jdbcTemplate.queryForObject(
                "$BASE_QUERY where uid = :uid",
                MapSqlParameterSource("uid", uid),
                this::mapRow
        )

    fun mapRow(resultSet: ResultSet, rowNum: Int) =
            Movie(
                resultSet.getString("uid"),
                resultSet.getString("title"),
                resultSet.getInt("year")
            )
}

@RestController
@RequestMapping("/movies/")
class MovieController(val movieRepository: MovieRepository) {
    @GetMapping("")
    fun findAll() = movieRepository.find()

    @GetMapping("{uid}")
    fun getById(@PathVariable("uid") uid: String) = movieRepository.get(uid)
}