package com.example.kkirikkiri.view.recyclerview.myteam.team

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.Category
import com.example.kkirikkiri.databinding.ItemMemberBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.viewmodel.TeamJoinModel
import com.example.kkirikkiri.viewmodel.TeamModel

class MyTeamAdapter(var listData : List<TeamMemberItem>, private val intent: Intent, private val activity: Activity) : RecyclerView.Adapter<MyTeamAdapter.TeamViewHolder>() {

    private val model = TeamModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TeamViewHolder(binding, intent, activity)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val member = listData[position]
        holder.setMember(member)
    }

    override fun getItemCount(): Int = listData.size

    inner class TeamViewHolder(val binding : ItemMemberBinding, intent: Intent, activity: Activity) : RecyclerView.ViewHolder(binding.root) {

        fun setMember(member : TeamMemberItem) {
            if (UserInfo.rule == Category.LEADER) {
                if (!member.access) {
                    itemSetting(binding, member)
                    binding.isNotAccept.visibility = View.VISIBLE
                    itemView.setOnClickListener { model.approveMember(UserInfo.teamId!!, member.id) }
                    binding.itemMemberName.setOnClickListener { model.approveMember(UserInfo.teamId!!, member.id) }
                    binding.isNotAccept.setOnClickListener { model.approveMember(UserInfo.teamId!!, member.id) }
                } else {
                    itemSetting(binding, member)
                    binding.itemMemberDelete.setOnClickListener { model.deleteMember(UserInfo.teamId!!, member.id) }
                }
            } else {
                if (!member.access) {
                    itemSetting(binding,member)
                    binding.isNotAccept.visibility = View.VISIBLE
                }
                itemSetting(binding, member)
            }
        }

    }

    private fun itemSetting(binding: ItemMemberBinding, member : TeamMemberItem) {
        binding.itemMemberName.text = member.name
        binding.temaMemberId.text = member.id.toString()
        if (member.category == Category.LEADER) binding.itemMemberDelete.visibility = View.INVISIBLE
        else if (UserInfo.rule == Category.LEADER) binding.itemMemberDelete.visibility = View.VISIBLE
        else binding.itemMemberDelete.visibility = View.INVISIBLE
        binding.itemMemberDelete.setOnClickListener { model.deleteMember(UserInfo.teamId!!, member.id) }
    }
}

