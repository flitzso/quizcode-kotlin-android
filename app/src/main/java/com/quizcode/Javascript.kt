package com.quizcode


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.quizcode.databinding.ActivityJavascriptBinding

class Javascript : AppCompatActivity() {

    private lateinit var binding: ActivityJavascriptBinding

    private val questions = arrayOf(
        "Quem criou o Javascript?",
        "Qual palavra-chave é usada para declarar uma variável em JavaScript?",
        "Qual método é usado para adicionar um elemento a um array em JavaScript?",
        "Qual dos seguintes operadores é usado para comparar a igualdade de valor e tipo em JavaScript?",
        "Qual é a estrutura de repetição em JavaScript que executa um bloco de código enquanto uma condição for verdadeira?",
        "Qual dos seguintes símbolos é usado para denotar um comentário de uma única linha em JavaScript?",
        "Qual tipo de dado é usado para representar valores verdadeiros ou falsos em JavaScript?",
        "Qual método é usado para remover o último elemento de um array em JavaScript?",
        "Qual é a palavra-chave usada para declarar uma função em JavaScript?",
        "Qual é o operador lógico usado para verificar se ambas as condições são verdadeiras em JavaScript?"

    )

    private val options = arrayOf(
        arrayOf("Rasmus Lerdorf", "Brendan Eich", "Guido van Rossum"),
        arrayOf("Func", "Var", "If"),
        arrayOf("Push", "Remove", "Replace"),
        arrayOf("==", "!=", "==="),
        arrayOf("For", "If", "While"),
        arrayOf("//", "**", "/* */"),
        arrayOf("Boolean", "String", "Null"),
        arrayOf("Pop", "Shift", "Unshift"),
        arrayOf("Define", "Func", "Function"),
        arrayOf("&&", "||", "!")
    )

    private val correctAnswers = arrayOf(1, 1, 0, 2, 2, 0, 0, 0, 2, 0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJavascriptBinding.inflate(layoutInflater)
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