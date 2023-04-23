package ru.sb066coder.shoplist.data

import ru.sb066coder.shoplist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        shopItem.id,
        shopItem.name,
        shopItem.amount,
        shopItem.active,
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        shopItemDbModel.name,
        shopItemDbModel.amount,
        shopItemDbModel.active,
        shopItemDbModel.id
    )

    fun mapListDbModelToListEntity(listDbModel: List<ShopItemDbModel>) = listDbModel.map {
        mapDbModelToEntity(it)
    }
}