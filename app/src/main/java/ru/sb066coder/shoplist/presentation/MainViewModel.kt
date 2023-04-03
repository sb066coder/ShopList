package ru.sb066coder.shoplist.presentation

import androidx.lifecycle.ViewModel
import ru.sb066coder.shoplist.data.ShopListRepositoryImpl
import ru.sb066coder.shoplist.domain.DeleteShopItemUseCase
import ru.sb066coder.shoplist.domain.GetShopListUseCase
import ru.sb066coder.shoplist.domain.ShopItem
import ru.sb066coder.shoplist.domain.UpdateShopItemUseCase

class MainViewModel: ViewModel() {

    // FIXME("упрощенный вариант поставки зависимости, нарушающий архитектуру приложения -
    //  обращение из presentation layer в data layer")
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val updateShopItemUseCase = UpdateShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(item: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(item)
    }

    fun ShopItemStateChange(item: ShopItem) {
        updateShopItemUseCase.updateShopItem(item.copy(active = !item.active))
    }
}