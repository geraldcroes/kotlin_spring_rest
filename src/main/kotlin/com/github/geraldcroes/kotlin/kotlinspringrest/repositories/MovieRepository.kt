package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.Movie

interface MovieRepository {
    fun get(uid: String): Movie
    fun find(): List<Movie>
}