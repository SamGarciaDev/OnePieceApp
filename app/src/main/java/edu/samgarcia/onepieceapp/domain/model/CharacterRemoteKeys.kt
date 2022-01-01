package edu.samgarcia.onepieceapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_REMOTE_KEYS_TABLE_NAME

@Entity(tableName = CHARACTER_REMOTE_KEYS_TABLE_NAME)
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
