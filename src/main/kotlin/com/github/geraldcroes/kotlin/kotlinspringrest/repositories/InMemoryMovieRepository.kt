package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import org.springframework.stereotype.Repository

@Repository
class InMemoryMovieRepository : MovieRepository {
    override fun get(uid: String) = Datas.movies().first { it.uid == uid }

    override fun find() = Datas.movies()
}