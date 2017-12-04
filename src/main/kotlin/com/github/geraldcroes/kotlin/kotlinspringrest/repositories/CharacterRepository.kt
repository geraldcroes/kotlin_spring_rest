package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.Character

interface CharacterRepository {
    fun get(uid: String): Character
    fun find(): List<Character>
}