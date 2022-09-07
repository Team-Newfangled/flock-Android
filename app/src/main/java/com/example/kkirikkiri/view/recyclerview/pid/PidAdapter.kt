package com.example.kkirikkiri.view.recyclerview.pid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemPidBigBinding

class PidAdapter(val list : List<PidItem>) : RecyclerView.Adapter<PidAdapter.Holder>() {
    inner class Holder(var binding : ItemPidBigBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setPid(item : PidItem) {
            binding.pidBigId.text = item.id.toString()
            binding.pidBigTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPidBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pid = list[position]
        holder.setPid(pid)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}