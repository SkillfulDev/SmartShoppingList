package ua.chernonog.smartshoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.chernonog.smartshoppinglist.data.ShopListRepositoryImpl
import ua.chernonog.smartshoppinglist.domain.AddShopItemUseCase
import ua.chernonog.smartshoppinglist.domain.DeleteShopItemUseCase
import ua.chernonog.smartshoppinglist.domain.EditShopItemUseCase
import ua.chernonog.smartshoppinglist.domain.GetShopItemUseCase
import ua.chernonog.smartshoppinglist.domain.GetShopListUseCase
import ua.chernonog.smartshoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)

    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}