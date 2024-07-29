package ua.chernonog.smartshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.chernonog.smartshoppinglist.domain.ShopItem
import ua.chernonog.smartshoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private val liveData = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, true)
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


    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemById(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)

    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Can`t find element with id $shopItemId")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
       return liveData

    }
    private fun updateList(){
        liveData.value = shopList.toList()
    }
}