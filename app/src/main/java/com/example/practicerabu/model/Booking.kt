package com.example.practicerabu.model

import android.graphics.Bitmap

data class Booking(
    val name:String,
    val email:String,
    val photo: Bitmap? = null
)
