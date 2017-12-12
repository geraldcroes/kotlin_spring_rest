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
class MovieTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    @Before
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["data.sql"])
    fun setup() {
        mvc = webAppContextSetup(context).build()
    }

    @Test
    fun `should retrieve all 3 movies from the database`() {
        mvc.perform(get("/movies/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(3)))
    }

    @Test
    fun `should retrieve The Matrix from its ID`() {
        mvc.perform(get("/movies/TM1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", `is`("The Matrix")))
                .andExpect(jsonPath("$.uid", `is`("TM1")))
                .andExpect(jsonPath("$.year", `is`(1999)))
    }

    @Test
    fun `should send a 404 when requesting for an unknown movie` () {
        mvc.perform(get("/movies/FOO"))
                .andExpect(status().isNotFound())
    }
}