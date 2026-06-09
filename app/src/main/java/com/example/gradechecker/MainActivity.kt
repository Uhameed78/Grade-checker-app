package com.example.gradechecker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val scoreInput  = findViewById<EditText>(R.id.scoreEditText)
        val langInput   = findViewById<EditText>(R.id.langEditText)
        val actionButton = findViewById<Button>(R.id.calculateButton)
        val resultText  = findViewById<TextView>(R.id.resultTextView)

        Log.d("ExamChallenge", "Student grade app loaded")

        actionButton.setOnClickListener {
            val studentScore      = scoreInput.text.toString().toIntOrNull()
            val preferredLanguage = langInput.text.toString().trim().uppercase()
            var displayMessage    = ""

            // Step 2 — validate score using logical operators
            if (studentScore == null || studentScore < 0 || studentScore > 100) {
                displayMessage = "Invalid score! Please enter a number between 0 and 100."
                resultText.text = displayMessage
                Log.d("ExamChallenge", "Invalid score entered")
                return@setOnClickListener
            }

            // Step 3 — determine pass or fail
            val passed = studentScore >= 50
            Log.d("ExamChallenge", "Score: $studentScore | Passed: $passed")

            // Step 3 — when statement for language translation
            displayMessage = when (preferredLanguage) {
                "ZA_ZU" -> if (passed) "Halala! Uphasise!" else "Uzame futhi next time."
                "ZA_ST" -> if (passed) "Ke mosebetsi o motle! O fetile!" else "Leka hape nako e tlang."
                else    -> if (passed) "Congratulations! You passed!" else "Keep trying!"
            }

            Log.d("ExamChallenge", "Language: $preferredLanguage | Message: $displayMessage")
            resultText.text = displayMessage
        }
    }
}