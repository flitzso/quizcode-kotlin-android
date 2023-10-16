package com.quizcode

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.quizcode.databinding.ActivityHtmlBinding

class Html : AppCompatActivity() {

    private lateinit var binding: ActivityHtmlBinding

    private val questions = arrayOf(
        "Question 1",
        "Question 2",
        "Question 3",
        "Question 4",
        "Question 5",
        "Question 6",
        "Question 7",
        "Question 8",
        "Question 9",
        "Question 10"

    )

    private val options = arrayOf(
        arrayOf("Question 1", "Question 1", "Question 1"),
        arrayOf("Question 2", "Question 2", "Question 2"),
        arrayOf("Encapsulamento", "Polimorfismo", "Herança"),
        arrayOf("Question4", "Question4", "question4"),
        arrayOf("Question5", "Question5", "question5"),
        arrayOf("Question6", "Question6", "Question6"),
        arrayOf("Question7", "Question7", "Question7"),
        arrayOf("Question8", "Question8", "Question8"),
        arrayOf("Question9", "Question9", "Question9"),
        arrayOf("Question10", "Question10", "Question10")
    )

    private val correctAnswers = arrayOf(0, 2, 2, 1, 1, 2, 0, 1, 0, 2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHtmlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        if (currentQuestionIndex == questions.size - 1) {
            binding.restartButton.visibility = View.VISIBLE
        } else {
            binding.restartButton.visibility = View.GONE
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showResults(){
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion()
        } else {
            binding.restartButton.visibility = View.VISIBLE
            showResults()
        }
    }

    private fun restartQuiz() {
        binding.restartButton.visibility = View.GONE

        currentQuestionIndex = 0
        score = 0
        displayQuestion()
    }
}