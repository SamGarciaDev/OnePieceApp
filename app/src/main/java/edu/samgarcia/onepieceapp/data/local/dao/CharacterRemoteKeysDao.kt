package edu.samgarcia.onepieceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.samgarcia.onepieceapp.domain.model.CharacterRemoteKeys
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_REMOTE_KEYS_TABLE_NAME

@Dao
interface CharacterRemoteKeysDao {
    @Query("SELECT * FROM $CHARACTER_REMOTE_KEYS_TABLE_NAME WHERE id=:characterId")
    suspend fun getRemoteKeys(characterId: Int): CharacterRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM $CHARACTER_REMOTE_KEYS_TABLE_NAME")
    suspend fun deleteAllRemoteKeys()
}