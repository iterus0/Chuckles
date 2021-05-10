package xyz.iterus.chuckles.di

import xyz.iterus.chuckles.jokes.di.JokesModule

object KoinModules {

    val modules = listOf(
        JokesModule.module
    )
}
