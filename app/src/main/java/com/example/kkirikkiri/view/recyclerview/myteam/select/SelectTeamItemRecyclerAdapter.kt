package com.example.kkirikkiri.view.recyclerview.myteam.select

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemSelectTeamProjectBinding
import com.example.kkirikkiri.module.network.dto.team.response.FindProjectResponse
import com.example.kkirikkiri.view.activity.project.ChangeProjectActivity
import com.example.kkirikkiri.view.activity.project.Project
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.viewmodel.ProjectModel

class SelectTeamItemRecyclerAdapter(var list : List<FindProjectResponse.Results>,var activity:Activity) : RecyclerView.Adapter<SelectTeamItemRecyclerAdapter.Holder>() {

    inner class Holder(var binding : ItemSelectTeamProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("수정", "삭제")
        private val model = ProjectModel()

        fun setProject(item : FindProjectResponse.Results) {
            if (!item.coverImage.isNullOrEmpty()) {
                val image = item.coverImage.replace("https://","").toUri()
                binding.projectImage.setImageURI(image)
            }
            binding.teamName.text = item.name

            itemView.setOnClickListener { Intent(itemView.context, Project::class.java).putExtra("id", item.id).putExtra("name", item.name).run { itemView.context.startActivity(this) } }

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) { dialog, pos ->
                    when (pos) {
                        0 -> {
                            Intent(itemView.context, ChangeProjectActivity::class.java).putExtra("name",item.name).putExtra("id", item.id).run { itemView.context.startActivity(this) }
                            dialog.dismiss()
                        }

                        1 -> {
                            model.deleteProject(item.id)
                            dialog.dismiss()
                         }
                    }
                }.show()

                false
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemSelectTeamProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setProject(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}