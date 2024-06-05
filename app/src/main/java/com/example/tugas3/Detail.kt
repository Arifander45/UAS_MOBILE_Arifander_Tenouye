package com.example.tugas3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas3.databinding.ActivityDetailBinding


class Detail : AppCompatActivity() {
private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intess = intent
        var asalT = intess.getStringExtra("ASALT")
        var deskT = intess.getStringExtra("DESKT")

        var imgT = intess.getStringExtra("IMGGT")
        var jdlT = intess.getStringExtra("NAMAT")


        binding.imagedetail.loadImage(imgT)
        binding.judulDetail.text =jdlT
        binding.asaldetail.text =asalT
        binding.deskDetail.text =deskT

    }
}