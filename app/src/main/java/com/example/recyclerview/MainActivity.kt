package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = CardAdapter()
    private val cardColor = listOf(
        R.color.white,
        R.color.black,
        R.color.grey,
        R.color.purple_200,
        R.color.purple_500,
        R.color.purple_700,
        R.color.teal_200,
        R.color.teal_700,)
    var k:Int = R.color.purple_700
    private var index = 0
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
                if (index == cardColor.size-1) {index = 0}
                val card = Card(cardColor[index], "Карточка № $index")
                Log.d("MYCARD", "index $index, color ${cardColor[index]}, R.color.purple ${R.color.purple_700}, k $k")
                adapter.addCard(card)
                index++
            }
        }
    }
}