package com.example.assignment2

import android.content.Intent
import android.os.Bundle
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

        txtScore.text = "Your score: $score / $total"

        val percentage = (score.toDouble() / total) * 100

        val feedback = when {
            percentage >= 80 -> "Well done! "
            percentage >= 50 -> "Not bad, keep improving "
            else -> "Keep practicing "
        }

        txtFeedback.text = feedback

        data class LifeHack(
            val statement: String,
            val isTrue: Boolean,
            val explanation: String
        )

        val statements = arrayOf(
            "Putting your phone in rice fixes water damage",
            "Cold showers boost alertness"
        )

        val explanations = arrayOf(
            "False: Rice does not properly dry internal components and can cause more damage.",
            "True: Cold showers increase circulation and make you feel more alert."
        )


        //what the "Review" button does //MENTION THE USE OF AI
        btnReview.setOnClickListener {

            var text = ""

            for (i in statements.indices) {
                text += "• ${statements[i]}\n"
                text += "${explanations[i]}\n\n"
            }

            txtReview.text = text
            txtReview.visibility = View.VISIBLE
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}