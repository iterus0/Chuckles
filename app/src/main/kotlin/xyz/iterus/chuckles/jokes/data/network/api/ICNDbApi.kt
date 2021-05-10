package xyz.iterus.chuckles.jokes.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.iterus.chuckles.jokes.data.network.response.ICNDbResponse
import xyz.iterus.chuckles.jokes.domain.model.Joke

interface ICNDbApi {

    @GET("jokes/random")
    suspend fun getRandomJoke(@Query("escape") escape: String? = "javascript"): ICNDbResponse<Joke>

    @GET("jokes/random/{number}")
    suspend fun getRandomJokes(
        @Path("number") numberOfJokes: Int,
        @Query("escape") escape: String? = "javascript"
    ): ICNDbResponse<List<Joke>>

    @GET("jokes/{id}")
    suspend fun getJokeById(
        @Path("id") id: Int,
        @Query("escape") escape: String? = "javascript"
    ): ICNDbResponse<Joke>

    @GET("jokes/count")
    suspend fun getNumberOfJokes(): ICNDbResponse<Int>
}
