package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import org.springframework.stereotype.Repository

@Repository
class InMemoryActorRepository : ActorRepository {
    override fun find() = Datas.actors()

    override fun get(uid: String) = Datas.actors().first { it.uid == uid }
}