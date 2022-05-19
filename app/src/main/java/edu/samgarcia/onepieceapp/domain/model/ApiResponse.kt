package edu.samgarcia.onepieceapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val characters: List<OPCharacter> = emptyList(),
    val lastUpdated: Long? = null
)
