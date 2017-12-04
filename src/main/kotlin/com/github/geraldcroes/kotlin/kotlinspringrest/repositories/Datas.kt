package com.github.geraldcroes.kotlin.kotlinspringrest.repositories

import com.github.geraldcroes.kotlin.kotlinspringrest.Acting
import com.github.geraldcroes.kotlin.kotlinspringrest.Actor
import com.github.geraldcroes.kotlin.kotlinspringrest.Character
import com.github.geraldcroes.kotlin.kotlinspringrest.Movie

object Datas {
    fun movies() = mutableListOf<Movie>(
            Movie("TM1", "The Matrix", 1999),
            Movie("TM2", "The Matrix Reloaded", 2003),
            Movie("TM3", "The Matrix Revolutions", 2003)
    )

    fun actors() = mutableListOf<Actor>(
            Actor("KR", "Keanu Reeves", mutableListOf(
                    Acting("TM1", "TA"),
                    Acting("TM2", "TA"),
                    Acting("TM3", "TA")
            )),
            Actor("LF", "Laurence Fishburne", mutableListOf(
                    Acting("TM1", "M"),
                    Acting("TM2", "M"),
                    Acting("TM3", "M")
            )),
            Actor("CAM", "Carrie-Anne Moss", mutableListOf(
                    Acting("TM1", "T"),
                    Acting("TM2", "T"),
                    Acting("TM3", "T")
            )),
            Actor("HW", "Hugo Weaving", mutableListOf(
                    Acting("TM1", "AS"),
                    Acting("TM2", "AS"),
                    Acting("TM3", "AS")
            )),
            Actor("MA", "Mary Alice", mutableListOf(
                    Acting("TM1", "TO"),
                    Acting("TM2", "TO")
            )),
            Actor("LW", "Lambert Wilson", mutableListOf(
                    Acting("TM2", "TM"),
                    Acting("TM3", "TM")
            )),
            Actor("MB", "Monica Bellucci", mutableListOf(
                    Acting("TM2", "P"),
                    Acting("TM3", "P")
            )),
            Actor("RDK", "Randall Duk Kim", mutableListOf(
                    Acting("TM2", "TKM")
            )),
            Actor("HB", "Helmut Bakaitis", mutableListOf(
                    Acting("TM2", "TAR"),
                    Acting("TM3", "TAR")
            )),
            Actor("TKA", "Tanveer K. Atwai", mutableListOf(
                    Acting("TM3", "S")
            ))
    )

    fun characters() = mutableListOf(
            Character("TA", "Thomas Anderson"),
            Character("M", "Morpheus"),
            Character("T", "Trinity"),
            Character("AS", "Agent Smith"),
            Character("TO", "The Oracle"),
            Character("TM", "The Merovingian"),
            Character("P", "Persephone"),
            Character("TKM", "The Keymaker"),
            Character("TAR", "The Architect"),
            Character("S", "Sati")
    )
}