package ru.sb066coder.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sb066coder.shoplist.domain.ShopItem
import ru.sb066coder.shoplist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 1 until 10) {
            val item = ShopItem("Name $i", i.toFloat(),true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getShopItemById(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw RuntimeException("no such ShopItem id $id")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun updateShopItem(shopItem: ShopItem) {
        shopList.remove(getShopItemById(shopItem.id))
        addShopItem(shopItem)
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}