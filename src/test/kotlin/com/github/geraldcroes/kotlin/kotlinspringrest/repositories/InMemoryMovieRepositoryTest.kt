package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.KotlinSpringRestTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(KotlinSpringRestTestConfiguration::class))
class InMemoryMovieRepositoryTest {
    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Test(expected = MovieNotFoundException::class)
    fun `getting an unknown Movie should raise a MovieNotFoundException` () {
        movieRepository.get("foo")
    }
}