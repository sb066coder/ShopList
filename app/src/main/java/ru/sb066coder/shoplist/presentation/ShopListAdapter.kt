package ru.sb066coder.shoplist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import ru.sb066coder.shoplist.R
import ru.sb066coder.shoplist.databinding.ItemShopActiveBinding
import ru.sb066coder.shoplist.databinding.ItemShopInactiveBinding
import ru.sb066coder.shoplist.domain.ShopItem

class ShopListAdapter: ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    companion object {
        const val INACTIVE_ITEM = 0
        const val ACTIVE_ITEM = 1
        const val RV_MAX_POOL_SIZE = 5
    }


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ACTIVE_ITEM -> R.layout.item_shop_active
            INACTIVE_ITEM -> R.layout.item_shop_inactive
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).active) {
            ACTIVE_ITEM
        } else {
            INACTIVE_ITEM
        }
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopInactiveBinding -> {
                binding.shopItem = shopItem
            }
            is ItemShopActiveBinding -> {
                binding.shopItem = shopItem
            }
        }
    }

}