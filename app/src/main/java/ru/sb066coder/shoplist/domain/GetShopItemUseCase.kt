package ru.sb066coder.shoplist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItemById(id: Int): ShopItem {
        return shopListRepository.getShopItemById(id)
    }
}