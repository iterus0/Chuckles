package xyz.iterus.chuckles.jokes.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ICNDbResponse<T> (
    val type: String,
    val value: T
)
