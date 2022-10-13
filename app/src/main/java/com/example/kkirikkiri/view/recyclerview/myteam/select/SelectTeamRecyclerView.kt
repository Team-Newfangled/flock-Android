package com.example.kkirikkiri.view.recyclerview.myteam.select

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemSelectTeamBinding
import com.example.kkirikkiri.module.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.team.MyTeam

class SelectTeamRecyclerView(var list : List<ResultResponse.Result>) : RecyclerView.Adapter<SelectTeamRecyclerView.Holder>(){

    inner class Holder(var binding : ItemSelectTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setTeams(item : ResultResponse.Result) {
            binding.teamId.text = item.teamId.toString()
            binding.teamName.text = item.teamName

            itemView.setOnClickListener {
                UserInfo.teamId = item.teamId
                val intent = Intent(itemView.context, MyTeam::class.java)
                intent.putExtra("id", item.teamId)
                intent.run { itemView.context.startActivity(intent) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSelectTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setTeams(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}