package ua.chernonog.smartshoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import ua.chernonog.smartshoppinglist.R
import ua.chernonog.smartshoppinglist.domain.ShopItem

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            Log.d("MainActivityTest", it.toString())
            val item = it[0]
            while (counter == 0) {
                viewModel.changeEnableState(item )
                counter++
            }
        }


    }
}
