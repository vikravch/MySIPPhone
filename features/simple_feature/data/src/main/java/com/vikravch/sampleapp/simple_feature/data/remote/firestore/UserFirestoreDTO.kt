package com.vikravch.sampleapp.simple_feature.data.remote.firestore

class UserFirestoreDTO(
    val id: String = "0",
    val name: String,
    val email: String
){
    constructor() : this("", "", "")

    override fun toString(): String {
        return "User(id='$id', name='$name', email='$email')"
    }

}