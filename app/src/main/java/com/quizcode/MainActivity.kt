package com.quizcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quizcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJavascript.setOnClickListener {

            val navegarSegundaTela = Intent(this,Javascript::class.java)
            startActivity(navegarSegundaTela)
        }
        binding.btnHtml.setOnClickListener {

            val navegarSegundaTela = Intent(this,Html::class.java)
            startActivity(navegarSegundaTela)
        }
        binding.btnCss.setOnClickListener {

            val navegarSegundaTela = Intent(this,Css::class.java)
            startActivity(navegarSegundaTela)
        }
    }
}