package com.example.lista1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class EndScreenActivity : AppCompatActivity() {
    private val point_text: TextView by lazy{findViewById(R.id.tv_points)}
    private val cheated_times_text: TextView by lazy { findViewById(R.id.tv_cheated_times) }
    private val correct_ans_text: TextView by lazy { findViewById(R.id.tv_c_ans) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)
        val bundle = intent.extras
        val points = bundle!!.getInt("pointsCounter")
        val cheated = bundle!!.getInt("cheatedTimes")
        val correct = bundle!!.getInt("correctCounter")

        point_text.text = "Points: $points"
        cheated_times_text.text = "Cheated $cheated times"
        correct_ans_text.text = "Correct answers: $correct"
    }

    fun backButtonPress(view: View?){
        val intentToStart = Intent(this, MainActivity::class.java)
        startActivity(intentToStart)
    }
}