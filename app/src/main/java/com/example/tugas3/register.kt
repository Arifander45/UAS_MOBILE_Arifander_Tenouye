package com.example.tugas3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tugas3.databinding.ActivityMainBinding
import com.example.tugas3.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.RegisterButton.setOnClickListener {
            val email : String = binding.email.text.toString().trim()
            val password : String = binding.password.text.toString().trim()
            val  confirmPassword : String = binding.konfirmasi.text.toString().trim()

            if (email.isEmpty()){
                binding.email.error = "Input Email"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error ="Invalid Password"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                binding.password.error = "password be more tthan 6 characters"
                binding.password.requestFocus()
                return@setOnClickListener            }

            if(password != confirmPassword){
                binding.password.error = "password mus be match"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }


        binding.login.setOnClickListener {
            val IntentHome = Intent(this, MainActivity::class.java)
            startActivity(IntentHome)
        }
    }

    private fun registerUser(email: String, password: String) {

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}