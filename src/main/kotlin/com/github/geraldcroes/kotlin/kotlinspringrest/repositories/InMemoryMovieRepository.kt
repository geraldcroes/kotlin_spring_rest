package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import org.springframework.stereotype.Repository

@Repository
class InMemoryMovieRepository : MovieRepository {
    override fun find() = Datas.movies()

    override fun get(uid: String) = try {
        Datas.movies().first { it.uid == uid }
    } catch (exception: NoSuchElementException) {
        throw MovieNotFoundException(uid)
    }
}