package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import org.springframework.stereotype.Repository

@Repository
class InMemoryCharacterRepository : CharacterRepository {
    override fun get(uid: String) = Datas.characters().first { it.uid == uid }

    override fun find() = Datas.characters()
}