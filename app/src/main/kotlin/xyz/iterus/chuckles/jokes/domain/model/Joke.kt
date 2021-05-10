package xyz.iterus.chuckles.jokes.domain.model

import com.squareup.moshi.JsonClass

// TODO: use separate class for domain, implement mappers
@JsonClass(generateAdapter = true)
data class Joke(
    val id: Int,
    val joke: String,
    val categories: List<String>
)
