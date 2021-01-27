package com.jemandandere.deeds.data.room

import androidx.lifecycle.LiveData
import com.jemandandere.deeds.data.DatabaseRepository
import com.jemandandere.deeds.model.Deed

class RoomRepository(
    private val deedDao: DeedDao
) : DatabaseRepository {

    override val allDeeds: LiveData<List<Deed>>
        get() = deedDao.getAll()

    override suspend fun insert(deed: Deed, onSuccess: () -> Unit) {
        deedDao.insert(deed)
        onSuccess()
    }

    override suspend fun update(deed: Deed, onSuccess: () -> Unit) {
        deedDao.update(deed)
        onSuccess()
    }

    override suspend fun delete(deed: Deed, onSuccess: () -> Unit) {
        deedDao.delete(deed)
        onSuccess()
    }
}