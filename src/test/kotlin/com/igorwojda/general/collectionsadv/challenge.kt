package com.igorwojda.general.collectionsadv

import org.junit.jupiter.api.Test

//https://www.youtube.com/watch?v=N4CpLxGJlq0&ab_channel=Kotlin

fun main(){
    println("Anyone has DL? ${people.any { it.hasDriverLicense }}")
    println("Can go into clubs? ${people.none { it.age < 18 }}")
    println("Can go into clubs2? ${people.all { it.age > 18 }}")
    println("Chunks: ${people.chunked(2)}")
    println("Windowed: ${people.windowed(2)}")
    println("Araras people: ${people.zipInfo(cities).filter { it.second == "Araras" }}")
    println( people.sortedBy { it.name }.groupBy { it.age }.toSortedMap() )
    people.groupBy { it.name.first() }.toSortedMap { a, b -> a.compareTo(b) }
}

fun List<Person>.zipInfo( info : List<String>) = this.zip( info ) { a, b -> a.name to b  }

val people = listOf(
    Person(
        name = "John",
        age = 10
    ),
    Person(
        name = "Jack",
        age = 29,
        hasDriverLicense = true
    ),
    Person(
        name = "Cecilia",
        age = 33
    ),
    Person(
        name = "Roberta",
        age = 31
    ),
    Person(
        name = "Ricardo",
        age = 10
    ),
)

val cities = listOf("Araras","Conchal","Araras","Limeira","Leme")

data class Person (
    val name : String,
    val age : Int,
    val hasDriverLicense : Boolean = false
)

private class Test {

    @Test
    fun `Result has N commas`() {

    }
}

