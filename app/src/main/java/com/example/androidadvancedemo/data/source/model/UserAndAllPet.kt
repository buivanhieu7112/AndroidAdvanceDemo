package com.example.androidadvancedemo.data.source.model

import androidx.room.Embedded
import androidx.room.Relation

class UserAndAllPet {
    @Embedded
    var user: User? = null
    @Relation(
        parentColumn = "id", entityColumn = "user_id"
    )
    var pets: MutableList<Pet> = mutableListOf()
}
