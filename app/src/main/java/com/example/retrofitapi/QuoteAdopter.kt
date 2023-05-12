package com.example.retrofitapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.databinding.QuotesLayoutBinding


class QuoteAdopter(var context: Context, val data: responsePost): RecyclerView.Adapter<QuoteAdopter.MyViewHolder>(){
    class MyViewHolder(var binding: QuotesLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = QuotesLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data.get(position)
        holder.binding.id.text = data.id.toString()
        holder.binding.body.text = data.body.toString()
        holder.binding.title.text = data.title.toString()
        holder.binding.userId.text = data.userId.toString()
    }
}
