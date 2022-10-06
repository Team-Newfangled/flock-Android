package com.example.kkirikkiri.view.recyclerview.project.projectpid

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemPidBinding
import com.example.kkirikkiri.view.activity.project.pid.PidView
import com.example.kkirikkiri.view.recyclerview.pid.PidItem

class ProjectPidAdapter(var list : List<PidItem>) : RecyclerView.Adapter<ProjectPidAdapter.Holder>(){

    val pids = list

    inner class Holder(var binding : ItemPidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setPid(item : PidItem) {
            binding.pidId.text = item.id.toString()
            binding.itemPidTitle.text = item.title

            itemView.setOnClickListener {
                Intent(itemView.context, PidView::class.java)
                    .putExtra("title", item.title)
                    .putExtra("id", item.id)
                    .putExtra("writeId", item.writeId)
                    .run { itemView.context.startActivity(this) }
            }
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