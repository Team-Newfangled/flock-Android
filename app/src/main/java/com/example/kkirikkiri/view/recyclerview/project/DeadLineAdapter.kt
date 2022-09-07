package com.example.kkirikkiri.view.recyclerview.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemDeadlineBinding

class DeadLineAdapter(val list : List<DeadLineItem>) : RecyclerView.Adapter<DeadLineAdapter.Holder>(){

    var deadlineList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemDeadlineBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val deadline = deadlineList[position]
        holder.setDeadLine(deadline)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(var binding : ItemDeadlineBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setDeadLine(deadLine : DeadLineItem) {
            binding.deadlineId.text = deadLine.id.toString()
            binding.itemDeadlineTime.text = "D-${deadLine.deadLineTime}"
            binding.itemDeadlineTitle.text = deadLine.title
        }
    }

}