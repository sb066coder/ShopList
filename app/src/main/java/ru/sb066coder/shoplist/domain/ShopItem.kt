package ru.sb066coder.shoplist.domain

data class ShopItem(
    val name: String,
    val count: Float,
    val active: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
