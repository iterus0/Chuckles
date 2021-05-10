package xyz.iterus.chuckles.jokes.domain.repo

import xyz.iterus.chuckles.jokes.domain.model.Joke

interface JokesRepository {

    suspend fun getRandomJokes(numOfJokes: Int): List<Joke>
}
