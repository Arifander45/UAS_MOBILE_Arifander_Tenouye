package com.example.tugas3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class kopiAdap (var mContext: Context, var kopiList: List<DataItem>):
    RecyclerView.Adapter<kopiAdap.ListViewHolder>() {
    inner class ListViewHolder(var  v: View) : RecyclerView.ViewHolder(v) {

        val imgTT = v.findViewById<ImageView>(R.id.imgD)

        val namaTT = v.findViewById<TextView>(R.id.kopiD)
        val asalTT = v.findViewById<TextView>(R.id.asalD)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kopiAdap.ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.data, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: kopiAdap.ListViewHolder, position: Int) {
        val newList = kopiList[position]
        holder.imgTT.loadImage(newList.imgurl)
        holder.namaTT.text = newList.nama
        holder.asalTT.text = newList.asal

        holder.v.setOnClickListener {

            val asal = newList.asal
            val desk = newList.deskripsi
            val imgg = newList.imgurl
            val nama = newList.nama


            val mIntent = Intent(mContext, Detail::class.java)
            mIntent.putExtra("ASALT", asal)
            mIntent.putExtra("DESKT", desk)

            mIntent.putExtra("IMGGT", imgg)
            mIntent.putExtra("NAMAT", nama)

            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return kopiList.size
    }
}




