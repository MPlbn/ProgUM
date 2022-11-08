package com.example.lista1

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.*
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val questionList = QuestionStorage.getQuestions()
    private var question_number = 1
    private var correct_count = 0
    private var points_counter = 0
    private var cheat_count = 0
    private val q_num_text: TextView by lazy{findViewById(R.id.question_number)}
    private val question_text: TextView by lazy{findViewById(R.id.question)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(intent.hasExtra("BUNDLE_POINTSCOUNTER")) {
            val bundleCheat = intent.extras
            question_number = bundleCheat!!.getInt("BUNDLE_Q_NUMBER", 1)
            correct_count = bundleCheat!!.getInt("BUNDLE_CORRECTCOUNTER", 0)
            points_counter = bundleCheat!!.getInt("BUNDLE_POINTSCOUNTER", 0)
            cheat_count = bundleCheat!!.getInt("BUNDLE_CHEATCOUNT", 0)
        }
        nextQuestion()
    }

    fun nextQuestion(){
        q_num_text.text = "Question number $question_number"
        question_text.text = questionList[question_number-1].question
    }

    fun trueButtonPress(view: View?){
        if(questionList[question_number-1].answer == true){
            correct_count++
            points_counter += 10
        }
        question_number++

        if(question_number > questionList.size){
            val intentEnd = Intent(this, EndScreenActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("correctCounter", correct_count)
            bundle.putInt("cheatedTimes", cheat_count)
            bundle.putInt("pointsCounter", points_counter)
            intentEnd.putExtras(bundle)

            startActivity(intentEnd)
        }
        else
            nextQuestion()


    }

    fun falseButtonPress(view: View?){
        if(questionList[question_number-1].answer == false){
            correct_count++
            points_counter += 10
        }
        question_number++

        if(question_number > questionList.size){
            val intentEnd = Intent(this, EndScreenActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("correctCounter", correct_count)
            bundle.putInt("cheatedTimes", cheat_count)
            bundle.putInt("pointsCounter", points_counter)
            intentEnd.putExtras(bundle)

            startActivity(intentEnd)
        }
        else
            nextQuestion()
    }

    fun cheatButtonPress(view: View?){
        cheat_count++
        points_counter -= 15


        val intentCheat = Intent(this, CheatActivity::class.java)

        val bundle = Bundle()
        bundle.putInt("BUNDLE_Q_NUMBER", question_number)
        bundle.putInt("BUNDLE_CORRECTCOUNTER", correct_count)
        bundle.putBoolean("BUNDLE_ANSWER", questionList[question_number-1].answer)
        bundle.putInt("BUNDLE_CHEATCOUNT", cheat_count)
        bundle.putInt("BUNDLE_POINTSCOUNTER", points_counter)
        intentCheat.putExtras(bundle)

        startActivity(intentCheat)
    }

    fun searchButtonPress(view: View?){
        val intentSearch = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        intentSearch.addCategory(CATEGORY_BROWSABLE)
        if(intentSearch.resolveActivity(packageManager) != null){
            startActivity(intentSearch)
        }
    }
}