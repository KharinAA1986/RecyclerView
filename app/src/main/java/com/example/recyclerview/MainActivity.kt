package com.example.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = CardAdapter()
    private val cardColor = listOf(
        Color.WHITE,
        Color.BLUE,
        Color.GREEN,
        Color.RED,
        Color.YELLOW,
        Color.CYAN,
        )
    private var index = 0
    private var cardNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        binding.apply {
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.adapter = adapter
            btnAdd.setOnClickListener {
                if (index > cardColor.size-1) {index = 0}
               val card = Card(cardColor[index], "Карточка № $cardNumber")
                adapter.addCard(card)
                index++
                cardNumber++
            }
        }
    }
}