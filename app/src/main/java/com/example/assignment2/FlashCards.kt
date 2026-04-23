package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlashCards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flash_cards)
        //Collecting components from UI
        val btnNext = findViewById<Button>(R.id.btnNext)
        val txtFlash = findViewById<TextView>(R.id.txtFlash)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)

        //array to store list of life hacks //MENTION USE OF AI
        data class LifeHack(val statement: String, val isTrue: Boolean, val review: String)

        val lifeHacks = arrayOf(
            LifeHack(
                "Putting your phone in rice fixes water damage",
                false,
                "Rice does not properly dry internal components."
            ),
            LifeHack(
                "Cold showers boost alertness",
                true,
                "They increase circulation and alertness."
            )
        )

        //Variable to store current flashcard
        var currentFlash = 0
        //keeping track of score
        var score = 0

        //showing the first item on the list
        txtFlash.text = lifeHacks[currentFlash].statement

        //what the "Next" button does
        btnNext.setOnClickListener {
            currentFlash++

            if (currentFlash < lifeHacks.size) {
                txtFlash.text = lifeHacks[currentFlash].statement
                txtFeedback.text = ""

                btnTrue.isEnabled = true
                btnFalse.isEnabled = true
            } else {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", lifeHacks.size)
                startActivity(intent)
                finish()

                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
                btnNext.isEnabled = false
        }
        }

        //what the "True" button does
        btnTrue.setOnClickListener {

            val correct = lifeHacks[currentFlash].isTrue

            if (correct) {
                txtFeedback.text = "Correct!"
                score ++
            } else {
                txtFeedback.text = "Wrong"

            }
            //disables the buttons so the user can press btnNext to continue with game
            btnTrue.isEnabled = false
            btnFalse.isEnabled = false
        }

        //what the "False" button does
        btnFalse.setOnClickListener {

            val correct = lifeHacks[currentFlash].isTrue

            if (!correct) {
                txtFeedback.text = "Correct!"
                score ++
            } else {
                txtFeedback.text = "Wrong"

            }
            //disables the buttons so the user can press btnNext to continue with game
            btnTrue.isEnabled = false
            btnFalse.isEnabled = false
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}