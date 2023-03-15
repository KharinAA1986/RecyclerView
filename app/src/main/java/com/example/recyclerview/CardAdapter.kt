package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.CardItemBinding

class CardAdapter(val listener: Listener): RecyclerView.Adapter<CardAdapter.CardHolder>() {
    private val cardList = ArrayList<Card>()
    class CardHolder (item: View): RecyclerView.ViewHolder(item) {
        private val binding = CardItemBinding.bind(item)
        fun bind (card: Card,listener: Listener) = with(binding){
            tvTitle.text = card.title
            cv.setBackgroundColor(card.backGroundColor)
            itemView.setOnClickListener{
                listener.onClick(card)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cardList[position],listener)
    }
    fun addCard (card:Card){
        cardList.add(card)
        notifyDataSetChanged()
    }

    fun removeCard (pos:Int){
        cardList.removeAt(pos)
        notifyItemRangeChanged(0,cardList.size)
        notifyItemRemoved(pos)
    }
    interface Listener {
        fun onClick(card: Card)
    }
}