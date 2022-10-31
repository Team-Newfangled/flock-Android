package com.example.kkirikkiri.calculater

import com.example.kkirikkiri.module.network.room.entity.TodoPercent

object Calculater {
    fun calculate(Todo : List<TodoPercent>) : Int {
        var sum = 0
        if (Todo.isEmpty()) {
            for (i in Todo) {
                sum += i.percent
            }
            sum /= Todo.size
        }
        return sum
    }
}