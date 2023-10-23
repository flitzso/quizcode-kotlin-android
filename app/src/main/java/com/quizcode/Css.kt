package com.quizcode

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.quizcode.databinding.ActivityCssBinding

class Css : AppCompatActivity() {

    private lateinit var binding: ActivityCssBinding

    private val questions = arrayOf(
        "Qual propriedade CSS é usada para definir a cor do texto?",
        "Qual propriedade CSS é usada para definir a cor de fundo de um elemento?",
        "Qual seletor CSS é usado para selecionar todos os elementos de uma página?",
        "Qual propriedade CSS é usada para definir a margem à direita de um elemento?",
        "Qual valor de exibição CSS faz com que um elemento seja exibido como um bloco?",
        "Qual propriedade CSS é usada para definir a largura de uma borda?",
        "Qual propriedade CSS é usada para definir a fonte em um elemento de texto?",
        "Qual propriedade CSS é usada para definir a espessura da fonte?",
        "Qual propriedade CSS é usada para alinhar o texto horizontalmente dentro de um elemento?",
        "Qual propriedade CSS é usada para definir a altura de uma área de texto?"

    )

    private val options = arrayOf(
        arrayOf("background-color", "text-color", "color"),
        arrayOf("text-color", "background-color", "foreground-color"),
        arrayOf("*", "all", " body"),
        arrayOf("margin-right", "right-margin", "margin-end"),
        arrayOf("inline", "flex", "block"),
        arrayOf("border-width", "border-size", "border-thickness"),
        arrayOf("font-family", "text-font", "font-style"),
        arrayOf("font-weight", "font-thickness", "text-weight"),
        arrayOf("text-align", "horizontal-align", "align-text"),
        arrayOf("height", "text-height", "area-height")
    )

    private val correctAnswers = arrayOf(2, 1, 0, 0, 2, 0, 0, 0, 0, 0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCssBinding.inflate(layoutInflater)
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
        //O bug e essa logica aqui
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
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