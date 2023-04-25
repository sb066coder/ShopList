package ru.sb066coder.shoplist.domain

data class ShopItem(
    val name: String,
    val amount: Float,
    val active: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
