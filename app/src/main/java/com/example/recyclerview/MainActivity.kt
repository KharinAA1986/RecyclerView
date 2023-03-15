package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val adapter = CardAdapter(this)
    private var index = 0
    private var cardNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         val cardColor = listOf(
                ContextCompat.getColor(applicationContext,R.color.red),
                ContextCompat.getColor(applicationContext,R.color.grey),
                ContextCompat.getColor(applicationContext,R.color.green),
                ContextCompat.getColor(applicationContext,R.color.purple_700),
                ContextCompat.getColor(applicationContext,R.color.teal_700),
                ContextCompat.getColor(applicationContext,R.color.purple_200),
                ContextCompat.getColor(applicationContext,R.color.teal_200),
        )
        init(cardColor)
    }
    private fun init(cardColor: List<Int>) {
        binding.apply {
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.adapter = adapter
            val touchHelper = getTouchMg()
            touchHelper.attachToRecyclerView(rv)
            btnAdd.setOnClickListener {
                if (index > cardColor.size-1) {index = 0}
               val card = Card(cardColor[index], "CARD № $cardNumber")
                adapter.addCard(card)
                index++
                cardNumber++
            }
        }

    }
    override fun onClick(card: Card) {
        Toast.makeText(this,"Нажата ${card.title}",Toast.LENGTH_LONG).show()
    }
    private fun getTouchMg(): ItemTouchHelper {
        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeCard(viewHolder.adapterPosition)
            }
        })
    }
}