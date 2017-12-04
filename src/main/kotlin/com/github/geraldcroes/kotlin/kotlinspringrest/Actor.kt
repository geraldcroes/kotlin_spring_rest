package com.github.geraldcroes.kotlin.kotlinspringrest

data class Actor(
    val uid: String,
    val name: String,
    val actingHistory: List<Acting>
)

data class Acting(
    val movieUid: String,
    val characterUid: String
)