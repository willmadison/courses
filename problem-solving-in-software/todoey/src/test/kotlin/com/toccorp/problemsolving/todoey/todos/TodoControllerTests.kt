package com.toccorp.problemsolving.todoey.todos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTests {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val testUserIds = arrayListOf<String>()

    private val random = Random()

    @BeforeEach
    internal fun setUp() {
        seedTestData()
    }

    private fun seedTestData() {
        generateTestUsers()
    }

    private fun generateTestUsers() {
        for (i in 0..random.nextInt(10)+1) {
            testUserIds.add(UUID.randomUUID().toString())
        }
    }

    @Test
    internal fun `It should save TODOs for a given user`() {
        val userId = testUserIds[random.nextInt(testUserIds.size)]
        val todo = Todo(userId = userId, contents = "TODO #${random.nextInt()}")
        val request = HttpEntity<Todo>(todo)
        val response = this.restTemplate.postForEntity("http://localhost:$port/api/todos/", request, Todo::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNotNull

        val created = response.body

        assertThat(created?.id).isNotEmpty
    }

    @Test
    internal fun `It should find all TODOs for a given user`() {
        val userId = testUserIds[random.nextInt(testUserIds.size)]
        val todo = Todo(userId = userId, contents = "TODO #${random.nextInt()}")
        val request = HttpEntity<Todo>(todo)
        this.restTemplate.postForEntity("http://localhost:$port/api/todos/", request, Todo::class.java)

        val todos = this.restTemplate.getForObject("http://localhost:$port/api/todos/?userId=$userId", Array<Todo>::class.java)
        assertThat(todos).isNotEmpty
        assertThat(todos.all { it.userId.equals(userId, true) }).isTrue
    }

    @Test
    internal fun `It finds find all TODOs for multiple users`() {
        val maxIterations = random.nextInt(5)+1
        var x = 0

        while (x < maxIterations) {
            val userId = testUserIds[random.nextInt(testUserIds.size)+1]
            val todo = Todo(userId = userId, contents = "TODO #${random.nextInt()}")
            val request = HttpEntity<Todo>(todo)
            this.restTemplate.postForEntity("http://localhost:$port/api/todos/", request, Todo::class.java)
            x++
        }

        x = 0
        while (x < maxIterations) {
            val userId = testUserIds[random.nextInt(testUserIds.size)+1]
            val todos = this.restTemplate.getForObject("http://localhost:$port/api/todos/?userId=$userId", Array<Todo>::class.java)
            assertThat(todos).isNotEmpty
            assertThat(todos.all { it.userId.equals(userId, true) }).isTrue
        }
    }


}
