package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.KotlinSpringRestTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(KotlinSpringRestTestConfiguration::class))
class InMemoryActorRepositoryTest {
    @Autowired
    private lateinit var actorRepository: ActorRepository

    @Test(expected = ActorNotFoundException::class)
    fun `getting an unknown Actor should raise an ActorNotFoundException` () {
        actorRepository.get("foo")
    }
}