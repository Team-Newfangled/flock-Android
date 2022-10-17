package com.example.kkirikkiri.view.recyclerview.myteam.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemMemberBinding

class MyTeamAdapter(var listData : List<TeamMemberItem>) : RecyclerView.Adapter<MyTeamAdapter.TeamViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val member = listData[position]
        holder.setMember(member)
    }

    override fun getItemCount(): Int = listData.size

    inner class TeamViewHolder(val binding : ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setMember(member : TeamMemberItem) {
            binding.itemMemberName.text = member.name
            binding.temaMemberId.text = member.id.toString()
            binding.itemMemberDelete.visibility = View.INVISIBLE
        }

    }
}

