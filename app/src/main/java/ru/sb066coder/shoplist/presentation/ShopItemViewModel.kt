package ru.sb066coder.shoplist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sb066coder.shoplist.data.ShopListRepositoryImpl
import ru.sb066coder.shoplist.domain.AddShopItemUseCase
import ru.sb066coder.shoplist.domain.GetShopItemUseCase
import ru.sb066coder.shoplist.domain.ShopItem
import ru.sb066coder.shoplist.domain.UpdateShopItemUseCase

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    // FIXME("упрощенный вариант поставки зависимости, нарушающий архитектуру приложения -
    //  обращение из presentation layer в data layer")
    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val updateShopItemUseCase = UpdateShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputAmount = MutableLiveData<Boolean>()
    val errorInputAmount: LiveData<Boolean>
        get() = _errorInputAmount

    private val _currentShopItem = MutableLiveData<ShopItem>()
    val currentShopItem: LiveData<ShopItem>
        get() = _currentShopItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    fun getShopItem(id: Int) {
        val item =  getShopItemUseCase.getShopItemById(id)
        _currentShopItem.value = item
    }

    fun addShopItem(inputName: String?, inputAmount: String?) {
        val name = parseName(inputName)
        val amount = parseAmount(inputAmount)
        if (validateInput(name, amount)) {
            val shopItem = ShopItem(name, amount, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    private fun finishWork() {
        _closeScreen.value = Unit
    }

    fun updateShopItem(inputName: String?, inputAmount: String?) {
        val name = parseName(inputName)
        val amount = parseAmount(inputAmount)
        if (validateInput(name, amount)) {
            _currentShopItem.value?.let {
                val item = it.copy(name = name, amount = amount)
                updateShopItemUseCase.updateShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseAmount(inputAmount: String?): Float {
        val amount = try {
            inputAmount?.trim()?.toFloat() ?: 0.0F
        } catch (e: NumberFormatException) {
            0.0F
        }
        return amount
    }

    private fun validateInput(name: String, amount: Float): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (amount < 0.001F) {
            _errorInputAmount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputAmount() {
        _errorInputAmount.value = false
    }

}