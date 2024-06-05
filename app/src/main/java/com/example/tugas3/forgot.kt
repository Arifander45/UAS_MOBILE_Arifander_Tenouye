package com.example.tugas3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugas3.databinding.ActivityForgotBinding
import com.example.tugas3.databinding.ActivityMainBinding

class forgot : AppCompatActivity() {
   private lateinit var binding: ActivityForgotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val Intentlogin = Intent(this, MainActivity::class.java)
            startActivity(Intentlogin)
        }
    }
    }