package com.jemandandere.deeds.data

import androidx.lifecycle.LiveData
import com.jemandandere.deeds.model.Deed

interface DatabaseRepository {
    val allDeeds: LiveData<List<Deed>>

    suspend fun insert(deed: Deed, onSuccess: () -> Unit)

    suspend fun update(deed: Deed, onSuccess: () -> Unit)

    suspend fun delete(deed: Deed, onSuccess: () -> Unit)
}