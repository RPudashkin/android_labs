package com.example.unnamed.lab5

class Goal(description: String) {
    private val mDescription = description
    private val mId = description.hashCode()

    var done = 0
    fun description() = mDescription
    fun id() = mId
}