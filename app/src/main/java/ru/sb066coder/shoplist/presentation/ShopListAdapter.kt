package ru.sb066coder.shoplist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.sb066coder.shoplist.R
import ru.sb066coder.shoplist.domain.ShopItem

class ShopListAdapter: Adapter<ShopListAdapter.ShopItemViewHolder>() {

    companion object {
        const val INACTIVE_ITEM = 0
        const val ACTIVE_ITEM = 1
        const val RV_MAX_POOL_SIZE = 5
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ACTIVE_ITEM -> R.layout.item_shop_active
            INACTIVE_ITEM -> R.layout.item_shop_inactive
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].active) {
            ACTIVE_ITEM
        } else {
            INACTIVE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    class ShopItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCount: TextView = itemView.findViewById(R.id.tvCount)
    }
}