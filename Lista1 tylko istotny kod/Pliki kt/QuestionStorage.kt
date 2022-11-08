package com.example.lista1

object QuestionStorage{

    fun getQuestions(): ArrayList<Question>{
        val qList = ArrayList<Question>()
        val q1 = Question(1, "Trawa jest zielona", true)
        val q2 = Question(2, "Trawa jest czerwona", false)
        val q3 = Question(3, "Mike Wazowski jest zielony", true)
        val q4 = Question(4, "Program dziala", true)
        qList.add(q1)
        qList.add(q2)
        qList.add(q3)
        qList.add(q4)

        return qList
    }
}