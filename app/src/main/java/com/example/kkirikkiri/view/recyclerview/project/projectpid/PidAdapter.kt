package com.example.kkirikkiri.view.recyclerview.project.projectpid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemPidBinding

class PidAdapter(var list : List<PidItem>) : RecyclerView.Adapter<PidAdapter.Holder>(){

    val pids = list

    inner class Holder(var binding : ItemPidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setPid(item : PidItem) {
            binding.pidId.text = item.id.toString()
            binding.itemPidTitle.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPidBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pid = pids[position]
        holder.setPid(pid)
    }

    override fun getItemCount(): Int {
        return pids.size
    }
}