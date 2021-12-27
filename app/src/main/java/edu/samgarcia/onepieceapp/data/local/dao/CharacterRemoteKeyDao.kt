package edu.samgarcia.onepieceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.samgarcia.onepieceapp.domain.model.CharacterRemoteKey
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_REMOTE_KEY_TABLE_NAME

@Dao
interface CharacterRemoteKeyDao {
    @Query("SELECT * FROM $CHARACTER_REMOTE_KEY_TABLE_NAME WHERE id=:id")
    suspend fun getRemoteKey(id: Int): CharacterRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKey>)

    @Query("DELETE FROM $CHARACTER_REMOTE_KEY_TABLE_NAME")
    suspend fun deleteAllRemoteKeys()
}