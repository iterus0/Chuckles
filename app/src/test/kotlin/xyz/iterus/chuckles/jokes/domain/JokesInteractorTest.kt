package xyz.iterus.chuckles.jokes.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import xyz.iterus.chuckles.jokes.domain.interactor.JokesInteractor
import xyz.iterus.chuckles.jokes.domain.model.Joke
import xyz.iterus.chuckles.jokes.domain.repo.JokesRepository
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/*
TODO: Replace runBlocking with runBlockingTest
Currently fails with "This job has not completed yet"
*/

class JokesInteractorTest {

    private val repo = mockk<JokesRepository>()
    private val interactor = JokesInteractor(repo)

    @Test
    fun `returns correct list`() {
        val testJokes = listOf(
            Joke(1, "joke #1", emptyList()),
            Joke(5, "joke #5", listOf("nerdy")),
            Joke(3, "joke #3", emptyList()),
            Joke(42, "joke #42", listOf("nerdy", "explicit"))
        )
        coEvery { repo.getRandomJokes(testJokes.size) } returns testJokes

        val jokes = runBlocking { interactor.getRandomJokes(testJokes.size) }

        coVerify { repo.getRandomJokes(testJokes.size) }
        assertEquals(testJokes, jokes)
    }

    @Test
    fun `returns empty list when numOfJokes is zero`() {
        val empty = emptyList<Joke>()
        val count = 0
        coEvery { repo.getRandomJokes(count) } returns empty

        val jokes = runBlocking { interactor.getRandomJokes(count) }

        assertEquals(empty, jokes)
    }

    @Test
    fun `throws exception when numOfJokes is negative`() {
        val count = -10
        coEvery { repo.getRandomJokes(count) } returns emptyList()

        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                val jokes = interactor.getRandomJokes(count)
            }
        }
    }
}
