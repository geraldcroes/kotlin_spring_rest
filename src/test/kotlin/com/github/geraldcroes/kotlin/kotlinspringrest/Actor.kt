package com.github.geraldcroes.kotlin.kotlinspringrest

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
@WebAppConfiguration
class ActorTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    @Before
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["data.sql"])
    fun setup() {
        mvc = webAppContextSetup(context).build()
    }

    @Test
    fun `should retrieve all 10 actors from the database`() {
        mvc.perform(get("/actors/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(11)))
    }

    @Test
    fun `should retrieve The Matrix from its ID`() {
        mvc.perform(get("/actors/KR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", `is`("Keanu Reeves")))
                .andExpect(jsonPath("$.uid", `is`("KR")))
                .andExpect(jsonPath("$.actings[0].movieUid", `is`("TM1")))
                .andExpect(jsonPath("$.actings[0].characterUid", `is`("TA")))
    }

    @Test
    fun `should retrieve an actor even if they haven't played in any movie yet` () {
        mvc.perform(get("/actors/SG"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", `is`("Some Guy")))
                .andExpect(jsonPath("$.uid", `is`("SG")))
                .andExpect(jsonPath("$.actings", hasSize<Any>(0)))
    }

    @Test
    fun `should send a 404 when requesting for an unknown actor` () {
        mvc.perform(get("/actors/FOO"))
                .andExpect(status().isNotFound())
    }
}