package edu.samgarcia.onepieceapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.samgarcia.onepieceapp.domain.model.Character
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_TABLE_NAME

@Dao
interface CharacterDao {
    @Query("SELECT * FROM $CHARACTER_TABLE_NAME ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME WHERE id=:characterId")
    fun getSelectedCharacter(characterId: Int): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(character: List<Character>)

    @Query("DELETE FROM $CHARACTER_TABLE_NAME")
    suspend fun deleteAllCharacters()
}