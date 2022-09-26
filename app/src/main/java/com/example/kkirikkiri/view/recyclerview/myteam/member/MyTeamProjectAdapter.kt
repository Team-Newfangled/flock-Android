package com.example.kkirikkiri.view.recyclerview.myteam.member

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemProjectBinding
import com.example.kkirikkiri.view.activity.project.Project

class MyTeamProjectAdapter(private val list: List<TeamMemberProjectItem>) : RecyclerView.Adapter<MyTeamProjectAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val project = list[position]

        holder.setProject(project)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class Holder(var binding : ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setProject(project : TeamMemberProjectItem) {
            binding.itemProjectName.text = project.name
            binding.itemProjectPercent.text = "${project.percent}%"
            binding.projectId.text = project.id.toString()

            itemView.setOnClickListener {
                Intent(itemView.context, Project::class.java).putExtra("id", project.id).run { itemView.context.startActivity(this) }
            }
        }


    }
}