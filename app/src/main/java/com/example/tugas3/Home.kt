package com.example.tugas3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas3.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var kopiRecyclerView: RecyclerView

    private lateinit var  kopiAdapter: kopiAdap

    private lateinit var listKopi: MutableList<DataItem>
    private  var  mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        kopiRecyclerView = findViewById(R.id.hasilData)

        kopiRecyclerView.setHasFixedSize(true)
        kopiRecyclerView.layoutManager = LinearLayoutManager(this@Home)
        binding.myDataLoaderprogressBar.visibility = View.VISIBLE

        listKopi = ArrayList()
        kopiAdapter = kopiAdap(this@Home,listKopi)
        kopiRecyclerView.adapter = kopiAdapter


        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("dKopi")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener{

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Home,error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderprogressBar.visibility = View.VISIBLE
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                listKopi.clear()
                for (teacherSnapshot in snapshot.children){
                    val  upload = teacherSnapshot.getValue(DataItem::class.java)
                    upload!!.key = teacherSnapshot.key
                    listKopi.add(upload)
                }
                kopiAdapter.notifyDataSetChanged()
                binding.myDataLoaderprogressBar.visibility = View.GONE
            }

        })





        binding.logout.setOnClickListener {
            firebaseAuth.signOut()

            Intent(this,  MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }





        }

}
