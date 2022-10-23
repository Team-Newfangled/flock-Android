package com.example.kkirikkiri.view.recyclerview.project.deadline

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemDeadlineBinding
import com.example.kkirikkiri.view.activity.project.Progress

class DeadLineAdapter(val list : List<DeadLineItem>, private val activity: Activity) : RecyclerView.Adapter<DeadLineAdapter.Holder>(){

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
        private val arrayItem = arrayOf("수정", "삭제")

        @SuppressLint("SetTextI18n")
        fun setDeadLine(deadLine : DeadLineItem) {
            binding.deadlineId.text = deadLine.id.toString()
            binding.itemDeadlineTime.text = deadLine.deadLineTime
            binding.itemDeadlineTitle.text = deadLine.title

           itemView.setOnClickListener {
               activity.startActivity(Intent(itemView.context, Progress::class.java))
           }
        }

    }

}