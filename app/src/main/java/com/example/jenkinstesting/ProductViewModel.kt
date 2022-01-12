/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*   is unlawful and prohibited.
*/
package com.example.jenkinstesting

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class CharacteristicModel(
    val title: String,
    val value: String
)

class ProductViewModel: ViewModel() {

    private val _sku: MutableLiveData<String> = MutableLiveData("Артикул: 80133659")
    val sku: LiveData<String> = _sku

    private val _title: MutableLiveData<String> = MutableLiveData("Дрель аккумуляторная Dexter, 18В, Li-ion, 2 Ач")
    val title: LiveData<String> = _title

    private val _itemsInCart: MutableLiveData<Int> = MutableLiveData(0)
    val itemsInCart: LiveData<Int> = _itemsInCart

    private val _availableCount: MutableLiveData<Int> = MutableLiveData(9999)
    val availableCount: LiveData<Int> = _availableCount

    private val _pickupStoresCount: MutableLiveData<Int> = MutableLiveData(10)
    val pickupStoresCount: LiveData<Int> = _pickupStoresCount

    private val _characteristics: MutableLiveData<List<CharacteristicModel>> = MutableLiveData(
        listOf(
            CharacteristicModel("Толщина (мм)", "12.5"),
            CharacteristicModel("Вес, кг:", "8.8"),
            CharacteristicModel("Марка", "KNAUF"),
            CharacteristicModel("Страна производитель:", "Россия")
        )
    )
    val characteristics: LiveData<List<CharacteristicModel>> = _characteristics

    fun addToCart() {
        _itemsInCart.postValue(1)
    }
}