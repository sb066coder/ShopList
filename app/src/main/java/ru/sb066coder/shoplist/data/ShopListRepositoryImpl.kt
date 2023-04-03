package ru.sb066coder.shoplist.data

import ru.sb066coder.shoplist.domain.ShopItem
import ru.sb066coder.shoplist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private var shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun getShopItemById(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw RuntimeException("no such ShopItem id $id")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun updateShopItem(shopItem: ShopItem) {
        shopList.remove(getShopItemById(shopItem.id))
        shopList.add(shopItem)
    }
}