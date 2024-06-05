package com.example.tugas3

import com.google.firebase.database.Exclude

data class DataItem(
    val asal : String? = null,
    val deskripsi : String? = null,
    val imgurl : String? = null,
    val nama : String? = null,

    @get:Exclude
    @set:Exclude
    var key:String? = null

)
