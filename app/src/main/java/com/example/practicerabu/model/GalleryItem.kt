package com.example.practicerabu.model

import androidx.annotation.DrawableRes

data class GalleryItem(
    @DrawableRes val imgRes: Int,
    val name: String
)