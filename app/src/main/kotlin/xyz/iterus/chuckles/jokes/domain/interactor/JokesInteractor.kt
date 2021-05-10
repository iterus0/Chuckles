package xyz.iterus.chuckles.jokes.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.iterus.chuckles.jokes.domain.model.Joke
import xyz.iterus.chuckles.jokes.domain.repo.JokesRepository

class JokesInteractor(private val repo: JokesRepository) {

    suspend fun getRandomJokes(numOfJokes: Int): List<Joke> = withContext(Dispatchers.Default) {
        if (numOfJokes > 0) {
            repo.getRandomJokes(numOfJokes)
        } else {
            if (numOfJokes == 0) {
                emptyList()
            } else {
                throw IllegalArgumentException("Number of jokes can't be negative")
            }
        }
    }
}
