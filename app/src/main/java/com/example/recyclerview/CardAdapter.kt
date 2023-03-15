package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.CardItemBinding
import java.util.*


class CardAdapter(private val listener: Listener): RecyclerView.Adapter<CardAdapter.CardHolder>() {
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

    fun toggle (fromPos: Int, toPos: Int){
        if (fromPos < toPos) {
            for (i in fromPos until toPos) {
                Collections.swap(cardList, i, i + 1)
            }
        } else {
            for (i in fromPos downTo toPos + 1) {
                Collections.swap(cardList, i, i - 1)
            }
        }
        notifyItemMoved(fromPos, toPos)
    }
    interface Listener {
        fun onClick(card: Card)
    }
}