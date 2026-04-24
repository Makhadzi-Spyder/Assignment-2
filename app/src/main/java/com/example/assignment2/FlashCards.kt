package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlashCards : AppCompatActivity() {
    //array to store list of life hacks //MENTION USE OF AI
    data class LifeHack(val statement: String, val isTrue: Boolean, val review: String)
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


        val lifeHacks = arrayOf(
            LifeHack(
                "Putting your phone in rice fixes water damage",
                false,
                "Rice does not properly dry internal components."
            ),
            LifeHack(
               "Sneezing with your eyes open can cause a blood vessel to pop",
                true,
                "A tiny blood vessel could pop and leave a red, painless spot on the whites on your eyes"
            ),
            LifeHack(
                "Cold showers boost alertness",
                true,
                "They increase circulation and alertness."
            ),
            LifeHack(
                "Drinking milk makes your bones stronger",
                true,
                "The calcium and protein in milk can help build bone density"

            ),
            LifeHack(
                "Using a cold spoon can reduce eye puffiness",
                true,
                "Swelling and puffiness decreases due to cold temperatures"
            ),
            LifeHack(
                "Chewing gum can improve focus temporarily",
                true,
                "Boosts alertness due to the increase in blood flow to the brain"
            ),
            LifeHack(
                "Cracking your knuckles causes arthritis",
                false,
                "No science proves that arthritis is linked to knuckle cracking"
            ),
            LifeHack(
                "Swallowing fruits seeds will cause a plant to grow in your stomach",
                false,
                "Stomach acid breaks the seed down"
            ),
            LifeHack(
                "You only use 10% of your brain",
                false,
                "Science has proves that multiple parts of your brain are active at once"

            ),
            LifeHack(
                "Eating carrots will give you much better vision",
                false,
                "They improve eye health but cannot give better vision"
            ),
            LifeHack(
                "Rolling your eyes too much will leave them stuck like that",
                false,
                "Medically impossible"
            )
        )

        //Variable to store current flashcard
        var currentFlash = 0
        //keeping track of score
        var score = 0

        //showing the first item on the list
        txtFlash.text = lifeHacks[currentFlash].statement
        Log.d("FlashCards","Showing first question on startup")

        //what the "Next" button does
        btnNext.setOnClickListener {
            currentFlash++
            Log.d("FlashCards", "Next button clicked. Current index: $currentFlash")

            if (currentFlash < lifeHacks.size) {
                txtFlash.text = lifeHacks[currentFlash].statement
                txtFeedback.text = ""

                btnTrue.isEnabled = true
                btnFalse.isEnabled = true
            } else {
                Log.d("FlashCards", "Quiz finished. Score: $score")
                val intent = Intent(this, Score::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", lifeHacks.size)
                startActivity(intent)
                finish()
            }
        }

        //what the "True" button does
        btnTrue.setOnClickListener {

            val correct = lifeHacks[currentFlash].isTrue
            Log.d("FlashCards", "True button clicked. Correct answer: $correct")

            if (correct) {
                txtFeedback.text = "Correct!"
                score ++
                Log.d("FlashCards", "User is correct. Score: $score")
            } else {
                txtFeedback.text = "Wrong"
                Log.d("FlashCards", "User is wrong.")
            }
            //disables the buttons so the user can press btnNext to continue with game
            btnTrue.isEnabled = false
            btnFalse.isEnabled = false
        }

        //what the "False" button does
        btnFalse.setOnClickListener {

            val correct = lifeHacks[currentFlash].isTrue
            Log.d("FlashCards", "True button clicked. Correct answer: $correct")

            if (!correct) {
                txtFeedback.text = "Correct!"
                score ++
                Log.d("FlashCards", "User is correct. Score: $score")
            } else {
                txtFeedback.text = "Wrong"
                Log.d("FlashCards", "User is wrong.")

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