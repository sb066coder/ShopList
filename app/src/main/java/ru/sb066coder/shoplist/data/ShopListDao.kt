package ru.sb066coder.shoplist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Query("SELECT * FROM shop_items WHERE id = :shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id = :shopItemId")
    fun deleteShopItemById(shopItemId: Int)


}