package ru.sb066coder.shoplist.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun getShopItemById(id: Int): ShopItem
    fun getShopList(): List<ShopItem>
    fun updateShopItem(shopItem: ShopItem)
}