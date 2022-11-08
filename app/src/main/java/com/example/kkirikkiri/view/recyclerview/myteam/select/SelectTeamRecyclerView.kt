package com.example.kkirikkiri.view.recyclerview.myteam.select

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemSelectTeamBinding
import com.example.kkirikkiri.module.network.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.dto.TeamToProjectData
import com.example.kkirikkiri.module.network.dto.team.response.FindProjectResponse
import com.example.kkirikkiri.view.activity.team.MyTeam
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationWidth
import com.example.kkirikkiri.viewmodel.TeamModel
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

class SelectTeamRecyclerView(var teams : List<ResultResponse.Result>, var activity: Activity, var intent: Intent, var projects : ArrayList<TeamToProjectData>) : RecyclerView.Adapter<SelectTeamRecyclerView.Holder>(){

    val teamToProject = LinkedHashMap<Int, List<FindProjectResponse.Results>>()

    inner class Holder(var binding : ItemSelectTeamBinding, var activity: Activity, var intent: Intent) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("삭제")
        private val model = TeamModel()

        fun setTeams(item : ResultResponse.Result) {
            binding.teamId.text = item.teamId.toString()
            binding.teamName.text = item.teamName

            binding.projects.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
           if (!teamToProject[item.teamId].isNullOrEmpty()) binding.projects.adapter = SelectTeamItemRecyclerAdapter(teamToProject[item.teamId]!!, activity)
            binding.projects.addItemDecoration(RecyclerDecorationWidth(20))

            itemView.setOnClickListener {
                UserInfo.teamId = item.teamId
                val intent = Intent(itemView.context, MyTeam::class.java)
                intent.putExtra("id", item.teamId)
                intent.putExtra("name", item.teamName)
                intent.run { itemView.context.startActivity(intent) }
            }

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) { dialog, pos ->
                    when (pos) {
                        0 -> {
                            model.deleteTeam(item.teamId)
                            refresh()
                            dialog.dismiss()
                        }
                    }
                }.show()
                true
            }
        }

        private fun refresh() {
            activity.finish()
            activity.overridePendingTransition(0,0)
            activity.startActivity(intent)
            activity.overridePendingTransition(0,0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        teams.sortedBy { it.teamId }
        projects.sortBy { it.id }

        teamToProject.clear()
        for (i in projects.indices) {
            teamToProject[teams[i].teamId] = projects[i].projects
        }
        val binding = ItemSelectTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, activity, intent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setTeams(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}