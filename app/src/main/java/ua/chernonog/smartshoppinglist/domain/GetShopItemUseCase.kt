package ua.chernonog.smartshoppinglist.domain

class GetShopItemUseCase {

    fun getShopItemById(shopItemId:Int) : ShopItem{
        return ShopItem(1,"Bread",3,true)
    }
}