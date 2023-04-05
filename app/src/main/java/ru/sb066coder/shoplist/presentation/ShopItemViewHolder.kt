package ru.sb066coder.shoplist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.sb066coder.shoplist.R

class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName: TextView = itemView.findViewById(R.id.tvName)
    val tvCount: TextView = itemView.findViewById(R.id.tvCount)
}