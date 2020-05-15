package com.mreigar.cardwalletapp.cardlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mreigar.cardwalletapp.R
import com.mreigar.cardwalletapp.model.CardViewModel
import kotlinx.android.synthetic.main.layout_card_item.view.*

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var items: MutableList<CardViewModel> = mutableListOf()

    fun setCards(comments: List<CardViewModel>) {
        items.apply {
            clear()
            addAll(comments)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: CardViewModel) = with(itemView) {
            itemCardNumber.text = card.number
        }
    }
}