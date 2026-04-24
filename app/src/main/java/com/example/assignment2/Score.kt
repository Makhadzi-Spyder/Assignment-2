package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import javax.sql.DataSource

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback2)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val txtReview = findViewById<TextView>(R.id.txtReview)

        //first thing to appear when screen loads
        Log.d("ScoreScreen", "Received score: $score out of $total")

        txtScore.text = "Your score: $score / $total"

        val percentage = (score.toDouble() / total) * 100

        val feedback = when {
            percentage >= 80 -> "Well done! "
            percentage >= 50 -> "Not bad, keep improving "
            else -> "Keep practicing "
        }
        Log.d("ScoreScreen", "Calculated percentage: $percentage")

        txtFeedback.text = feedback


        val statements = arrayOf(
            "Putting your phone in rice fixes water damage",
            "Sneezing with your eyes open can cause a blood vessel to pop",
            "Cold showers boost alertness",
            "Drinking milk makes your bones stronger",
            "Using a cold spoon can reduce eye puffiness",
            "Chewing gum can improve focus temporarily",
            "Cracking your knuckles causes arthritis",
            "Swallowing fruits seeds will cause a plant to grow in your stomach",
            "You only use 10% of your brain",
            "Eating carrots will give you much better vision",
            "Rolling your eyes too much will leave them stuck like that"
        )

        val explanations = arrayOf(
            "False: Rice does not properly dry internal components and can cause more damage.",
            "True: A tiny blood vessel could pop and leave a red, painless spot on the whites on your eyes",
            "True: Cold showers increase circulation and make you feel more alert.",
            "True: The calcium and protein in milk can help build bone density",
            "True: Swelling and puffiness decreases due to cold temperatures",
            "True: Boosts alertness due to the increase in blood flow to the brain",
            "False: No science proves that arthritis is linked to knuckle cracking",
            "False: Stomach acid breaks the seed down",
            "False: Science has proves that multiple parts of your brain are active at once",
            "False: They improve eye health but cannot give better vision",
            "False: Medically impossible "
        )


        //what the "Review" button does //MENTION THE USE OF AI
        btnReview.setOnClickListener {
            Log.d("ScoreScreen", "Review button clicked")

            var reviewText = ""

            for (i in statements.indices) {
                reviewText += "• ${statements[i]}\n"
                reviewText += "${explanations[i]}\n\n"
            }

            txtReview.text = reviewText
            txtReview.visibility = View.VISIBLE
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}