package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import org.springframework.stereotype.Repository

@Repository
class InMemoryCharacterRepository : CharacterRepository {
    override fun find() = Datas.characters()

    override fun get(uid: String) = try {
        Datas.characters().first { it.uid == uid }
    } catch (exception: NoSuchElementException) {
        throw CharacterNotFoundException(uid)
    }
}