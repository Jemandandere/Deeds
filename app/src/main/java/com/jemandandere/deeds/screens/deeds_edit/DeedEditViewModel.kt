package com.jemandandere.deeds.screens.deeds_edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jemandandere.deeds.App
import com.jemandandere.deeds.model.Deed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeedEditViewModel(application: Application) : AndroidViewModel(application) {
    fun save(mDeed: Deed, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            App.instance.dbRepository.insert(mDeed, onSuccess)
        }
    }

    fun delete(mDeed: Deed, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            App.instance.dbRepository.delete(mDeed, onSuccess)
        }
    }

}