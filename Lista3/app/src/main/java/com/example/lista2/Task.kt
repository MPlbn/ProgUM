package com.example.lista2

data class Task(val id: Int, val title: String, val description: String) {  }

/*
fun generateListOfTasks(): MutableList<Task> {
    var listOfTasks = mutableListOf<Task>()
    for (i: Int in 0..15) {
        listOfTasks.add(Task("Task #$i", "This is task number $i; Lorem ipsum blablabla"))
    }
    return listOfTasks
}*/
