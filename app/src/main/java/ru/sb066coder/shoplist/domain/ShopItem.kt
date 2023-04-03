package ru.sb066coder.shoplist.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Float,
    val active: Boolean
)
