package edu.samgarcia.onepieceapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_REMOTE_KEY_TABLE_NAME

@Entity(tableName = CHARACTER_REMOTE_KEY_TABLE_NAME)
data class CharacterRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
