package com.example.lista1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    private val ans_text: TextView by lazy{findViewById(R.id.cheat_answer)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        val bundle = intent.extras
        val question_number = bundle!!.getInt("BUNDLE_Q_NUMBER")
        val answer = bundle.getBoolean("BUNDLE_ANSWER")


        ans_text.text = "Answer to question $question_number is: $answer"
    }

    fun closeButtonPress(view: View?){
        val bundleBack = intent.extras!!
        val intentMain = Intent(this, MainActivity::class.java)
        intentMain.putExtras(bundleBack)
        startActivity(intentMain)
    }
}