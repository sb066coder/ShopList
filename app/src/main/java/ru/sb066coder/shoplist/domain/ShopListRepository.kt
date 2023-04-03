package ru.sb066coder.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun getShopItemById(id: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
    fun updateShopItem(shopItem: ShopItem)
}