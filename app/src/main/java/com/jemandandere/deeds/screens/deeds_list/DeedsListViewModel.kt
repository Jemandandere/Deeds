package com.jemandandere.deeds.screens.deeds_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jemandandere.deeds.App
import com.jemandandere.deeds.model.Deed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeedsListViewModel(application: Application): AndroidViewModel(application) {
    val deeds = App.instance.dbRepository.allDeeds

    fun update(mDeed: Deed, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            App.instance.dbRepository.insert(mDeed, onSuccess)
        }
    }
}