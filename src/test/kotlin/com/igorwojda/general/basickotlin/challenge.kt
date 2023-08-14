package com.igorwojda.general.basickotlin

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

//https://www.udemy.com/course/kotlin-for-java-developers/learn/lecture/7792800#overview
//Lesson 61

fun main(){
    println(countTo(100))
    println(countTo2(100))
}

fun countTo(size : Int) =
    (1..size).joinToString(",") { it.toString() }

fun countTo2(size : Int) =
    StringBuilder().apply {
        (1..size-1).forEach {
            append(it)
            append(",")
        }
        append(size)
    }


private class Test {

    @Test
    fun `Result has N commas`() {
        val size = 100
        with(size){
            countTo(this)
                .count { it.isComma() }
        } shouldBeEqualTo (size-1)

        with(size){
            countTo2(this)
                .count { it.isComma() }
        } shouldBeEqualTo (size-1)
    }
}

fun Char.isComma() = this == ','