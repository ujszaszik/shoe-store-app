package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.model.Shoe
import com.udacity.shoestore.model.ShoeValueState
import com.udacity.shoestore.model.ValidationResult

class SharedShoeStoreViewModel : ViewModel() {

    private val _shoeValueValidity = MutableLiveData<ValidationResult>(ValidationResult.Valid)
    val shoeValueValidity: LiveData<ValidationResult> get() = _shoeValueValidity

    private val _shoeToSave = MutableLiveData<Shoe>()
    val shoeToSave: LiveData<Shoe> get() = _shoeToSave

    private val _shoeList = MutableLiveData<MutableSet<Shoe>>(mutableSetOf())
    val shoeList: LiveData<MutableSet<Shoe>> get() = _shoeList

    fun validateShoe(shoe: Shoe) {
        if (!shoe.areAllFieldsSet()) {
            _shoeValueValidity.postValue(ValidationResult.Incomplete)
            return
        } else if (isShoeAlreadyExist(shoe)) {
            _shoeValueValidity.postValue(ValidationResult.Existing)
            return
        } else {
            _shoeToSave.postValue(shoe)
        }
    }

    fun addNewShoe(shoe: Shoe) {
        _shoeList.value?.apply { add(shoe) }.let { _shoeList.postValue(it) }
        resetValidity()
    }

    private fun isShoeAlreadyExist(shoe: Shoe): Boolean = _shoeList.value?.contains(shoe) ?: false

    fun setShoeAsAlreadySent() {
        _shoeToSave.value?.state = ShoeValueState.ALREADY_SENT
    }

    fun isShoeAlreadySent(): Boolean {
        return _shoeToSave.value?.state == ShoeValueState.ALREADY_SENT
    }

    fun onCancelled() {
        _shoeValueValidity.postValue(ValidationResult.Cancelled)
    }

    fun resetValidity() {
        _shoeValueValidity.postValue(ValidationResult.Valid)
    }
}