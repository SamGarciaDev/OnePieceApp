package edu.samgarcia.onepieceapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTER_TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = CHARACTER_TABLE_NAME)
data class OPCharacter(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val img: String,
    val about: String,
    val origin: String,
    val bounty: Long,
    val rating: Double,
    val devilFruits: List<String>,
    val family: List<String>
)
