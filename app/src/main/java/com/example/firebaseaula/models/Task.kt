package com.example.firebaseaula.models

data class Task (
    val id: String = "",
    val name: String = "",
    val completed: Boolean = false,
    var isChecked: Boolean = false,
    val userId: String = ""
)