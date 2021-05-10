package xyz.iterus.chuckles.jokes.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.iterus.chuckles.jokes.data.network.response.ICNDbResponse
import xyz.iterus.chuckles.jokes.domain.model.Joke

interface ICNDbApi {

    @GET("jokes/random")
    suspend fun getRandomJoke(): ICNDbResponse<Joke>

    @GET("jokes/random/{number}")
    suspend fun getRandomJokes(@Path("number") numberOfJokes: Int): ICNDbResponse<List<Joke>>

    @GET("jokes/{id}")
    suspend fun getJokeById(@Path("id") id: Int): ICNDbResponse<Joke>

    @GET("jokes/count")
    suspend fun getNumberOfJokes(): ICNDbResponse<Int>
}
