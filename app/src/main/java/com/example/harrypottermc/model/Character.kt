package com.example.harrypottermc.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a Harry Potter character with a nested class for Wand.
 */
@Serializable
data class Character(
    val id: String,
    val name: String,
    @SerialName("alternate_names") val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    @SerialName("dateOfBirth") val dateOfBirth: String?,
    @SerialName("yearOfBirth") val yearOfBirth: Int?,
    val wizard: Boolean,
    val ancestry: String,
    @SerialName("eyeColour") val eyeColour: String,
    @SerialName("hairColour") val hairColour: String,
    val wand: Wand,
    val patronus: String,
    @SerialName("hogwartsStudent") val hogwartsStudent: Boolean,
    @SerialName("hogwartsStaff") val hogwartsStaff: Boolean,
    val actor: String,
    @SerialName("alternate_actors") val alternateActors: List<String>,
    val alive: Boolean,
    val image: String
) {
    @Serializable
    data class Wand(
        val wood: String,
        val core: String,
        val length: Float?,
    )
}