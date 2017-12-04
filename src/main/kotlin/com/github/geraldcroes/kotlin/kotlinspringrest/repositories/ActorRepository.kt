package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.Actor

interface ActorRepository {
    fun get(uid: String): Actor
    fun find(): List<Actor>
}