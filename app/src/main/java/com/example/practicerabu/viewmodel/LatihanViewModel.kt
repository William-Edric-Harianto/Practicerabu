package com.example.practicerabu.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.practicerabu.R
import com.example.practicerabu.model.Booking
import com.example.practicerabu.model.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LatihanViewModel : ViewModel() {

    private val _galleryList = MutableStateFlow(
        listOf(
            GalleryItem(R.drawable.cast_tom, "Tom Holland"),
            GalleryItem(R.drawable.cast_zen, "Zen Daya"),
            GalleryItem(R.drawable.cast_big, "Jacob Batalon"),
            GalleryItem(R.drawable.cast_tony, "Tony Stark"),
            GalleryItem(R.drawable.cast_tante, "Marisa Tomei"),
        )
    )
    val galleryList: StateFlow<List<GalleryItem>> = _galleryList.asStateFlow()

    //    Booking Logic
    private val _bookingList = MutableStateFlow<List<Booking>>(emptyList())
    val bookingList: StateFlow<List<Booking>> = _bookingList.asStateFlow()

    fun addBookingList(name: String, email: String, photo: Bitmap?) {
        _bookingList.update { it + Booking(name, email, photo) }
        _isFormOpen.value = false
    }


    fun deleteBooking(index: Int) {
        _bookingList.update { list ->
            if (index in list.indices) list.toMutableList().apply { removeAt(index) }
            else list
        }
    }


    //Buka Tutup Form Booking Logic
    private val _isFormOpen = MutableStateFlow(false)
    val isFormOpen: StateFlow<Boolean> = _isFormOpen.asStateFlow()

    fun openForm() {
        _isFormOpen.value = true
    }

    fun closeForm() {
        _isFormOpen.value = false
    }

}