package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

class ActorNotFoundException(val uid: String) : RuntimeException()
class CharacterNotFoundException(val uid: String) : RuntimeException()
class MovieNotFoundException(val uid: String) : RuntimeException()