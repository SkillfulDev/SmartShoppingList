package ua.chernonog.smartshoppinglist.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.chernonog.smartshoppinglist.R
import ua.chernonog.smartshoppinglist.domain.ShopItem

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }

    }

    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (shopItem in list) {

            val view = if (shopItem.enabled) {
                LayoutInflater.from(this).inflate(
                    R.layout.item_shop_enabled, llShopList, false
                )
            } else {
                LayoutInflater.from(this).inflate(
                    R.layout.item_shop_disabled, llShopList, false
                )
            }

            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            llShopList.addView(view)

            view.setOnLongClickListener {
                viewModel.changeEnableState(shopItem)
                true

            }
        }
    }
}
