package edu.samgarcia.onepieceapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_TABLE_NAME

@Dao
interface CharacterDao {
    @Query("SELECT * FROM $CHARACTER_TABLE_NAME ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, OPCharacter>

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME WHERE id=:characterId")
    fun getSelectedCharacter(characterId: Int): OPCharacter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<OPCharacter>)

    @Query("DELETE FROM $CHARACTER_TABLE_NAME")
    suspend fun deleteAllCharacters()
}