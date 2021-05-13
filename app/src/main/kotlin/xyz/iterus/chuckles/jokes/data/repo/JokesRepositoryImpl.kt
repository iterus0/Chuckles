package xyz.iterus.chuckles.jokes.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.iterus.chuckles.jokes.data.network.api.ICNDbApi
import xyz.iterus.chuckles.jokes.domain.repo.JokesRepository

class JokesRepositoryImpl(private val api: ICNDbApi) : JokesRepository {

    // TODO: Implement joke caching

    override suspend fun getRandomJokes(numOfJokes: Int) = withContext(Dispatchers.IO) {
        val response = api.getRandomJokes(numOfJokes)

        if (response.type == "success") {
            response.value
        } else {
            // TODO: Error handling
            emptyList()
        }
    }
}
