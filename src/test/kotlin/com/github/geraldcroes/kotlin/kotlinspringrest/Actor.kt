package com.github.geraldcroes.kotlin.kotlinspringrest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import org.hamcrest.Matchers.*
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
@WebAppConfiguration
class ActorTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    @Before
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["data.sql"])
    fun setup()
    {
        mvc = webAppContextSetup(context).build()
    }

    @Test
    fun `should retrieve all 10 actors from the database` () {
        mvc.perform(get("/actors/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(10)))
    }

    @Test
    fun `should retrieve The Matrix from its ID` () {
        mvc.perform(get("/actors/KR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", `is`("Keanu Reeves")))
                .andExpect(jsonPath("$.uid", `is`("KR")))
                .andExpect(jsonPath("$.actings[0].movie_uid", `is`("TM1")))
                .andExpect(jsonPath("$.actings[0].character_uid", `is`("TA")))
    }
}