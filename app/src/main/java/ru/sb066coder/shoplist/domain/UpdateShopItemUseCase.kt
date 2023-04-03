package ru.sb066coder.shoplist.domain

class UpdateShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun updateShopItem(shopItem: ShopItem) {
        shopListRepository.updateShopItem(shopItem)
    }
}