package com.jemandandere.deeds.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jemandandere.deeds.model.Deed

@Dao
interface DeedDao {
    @Query("select * from deeds")
    fun getAll() : LiveData<List<Deed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deed: Deed)

    @Update
    fun update(deed: Deed)

    @Delete
    fun delete(deed: Deed)
}