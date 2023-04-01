package com.example.codepathand101proj6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter(private val list: MutableList<Triple<String,String,String>>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateText: TextView
        val imageView: ImageView
        val descText: TextView

        init{
            dateText = view.findViewById(R.id.dateText)
            imageView = view.findViewById(R.id.imageView)
            descText = view.findViewById(R.id.descText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateText.text = list[position].first
        Glide.with(holder.itemView).load(list[position].second).centerCrop().into(holder.imageView)
        holder.descText.text = list[position].third
    }
}