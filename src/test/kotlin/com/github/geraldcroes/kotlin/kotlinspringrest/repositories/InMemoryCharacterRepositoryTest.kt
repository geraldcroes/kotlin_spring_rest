package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.KotlinSpringRestTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(KotlinSpringRestTestConfiguration::class))
class InMemoryCharacterRepositoryTest {
    @Autowired
    private lateinit var characterRepository: CharacterRepository

    @Test(expected = CharacterNotFoundException::class)
    fun `getting an unknown Character should raise a CharacterNotFoundException` () {
        characterRepository.get("foo")
    }
}