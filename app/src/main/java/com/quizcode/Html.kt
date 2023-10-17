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
        "Qual é a tag HTML usada para criar uma lista não ordenada?",
        "Qual é a tag HTML usada para criar um link?",
        "Qual elemento HTML é usado para criar um cabeçalho de primeiro nível?",
        "Qual tag HTML é usada para criar um parágrafo?",
        "Qual é a tag HTML usada para inserir uma quebra de linha?",
        "Qual é a tag HTML usada para criar uma tabela?",
        "Qual atributo HTML é usado para definir o texto alternativo de uma imagem?",
        "Qual é a tag HTML usada para criar uma lista ordenada?",
        "Qual elemento HTML é usado para criar uma linha horizontal?",
        "Qual tag HTML é usada para criar um link de email?"

    )

    private val options = arrayOf(
        arrayOf("<ol>", "<ul>", "<li>"),
        arrayOf("<a>", "<link>", "<href>"),
        arrayOf("<header>", "<h1>", "<head>"),
        arrayOf("<p>", "<para>", "<pr>"),
        arrayOf("<break>", "<br>", "<newline>"),
        arrayOf("<table>", "<tab>", "<t>"),
        arrayOf("<text>", "<description>", "<alt>"),
        arrayOf("<ol>", "<ul>", "<li>"),
        arrayOf("<line>", "<hr>", "<horizontal>"),
        arrayOf("<email>", "<link>", "<a>")
    )

    private val correctAnswers = arrayOf(1, 0, 1, 0, 1, 0, 2, 0, 1, 2)

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