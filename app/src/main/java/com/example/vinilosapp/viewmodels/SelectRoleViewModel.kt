package com.example.vinilosapp.viewmodels
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilosapp.R

class SelectRoleViewModel (application: Application) :  AndroidViewModel(application) {

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    // access the items of the list

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SelectRoleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SelectRoleViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}
