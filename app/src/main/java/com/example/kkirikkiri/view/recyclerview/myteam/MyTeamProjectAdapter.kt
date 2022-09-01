package com.example.kkirikkiri.view.recyclerview.myteam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemProjectBinding

class MyTeamProjectAdapter(private val list: List<TeamMemberProject>) : RecyclerView.Adapter<MyTeamProjectAdapter.Holder>() {


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
        fun setProject(project : TeamMemberProject) {
            binding.itemProjectName.text = project.name
            binding.itemProjectPercent.text = "${project.percent}%"
            binding.projectId.text = project.id.toString()
        }
    }
}