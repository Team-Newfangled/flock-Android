package com.example.kkirikkiri.view.recyclerview.progress

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemProjectBinding
import com.example.kkirikkiri.module.network.room.RoomImpl
import com.example.kkirikkiri.view.activity.project.todo.ChangeTodo
import com.example.kkirikkiri.view.activity.project.todo.ControlTodo
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.viewmodel.TodoModel

class PartProgressAdapter(val list : List<TeamMemberProjectItem>, val intent: Intent, val activity: Activity) : RecyclerView.Adapter<PartProgressAdapter.Holder>() {

    inner class Holder(val binding : ItemProjectBinding, val intent: Intent, val activity: Activity) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("수정", "삭제")
        private val model =  TodoModel()

        @SuppressLint("SetTextI18n")
        fun setPartProgress(item : TeamMemberProjectItem) {
            binding.projectId.text = item.id.toString()
            binding.itemProjectPercent.text = "${item.percent}%"
            binding.itemProjectName.text = item.name

            itemView.setOnClickListener {
                Intent(itemView.context, ControlTodo::class.java)
                    .putExtra("id", item.id)
                    .putExtra("name", item.name)
                    .putExtra("percent", item.percent)
                    .run { itemView.context.startActivity(this) }
            }

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) {dialog, pos ->
                    when (pos) {
                        0 -> {
                            Intent(itemView.context, ChangeTodo::class.java)
                                .putExtra("id", item.id)
                                .putExtra("name", item.name)
                                .putExtra("percent", item.percent)
                                .run { itemView.context.startActivity(this) }
                            dialog.dismiss()
                        }
                        1 -> {
                            model.deleteTodo(item.id)
                            refresh()
                            dialog.dismiss()
                        }
                    }
                }.show()
                false
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
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, intent, activity)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setPartProgress(list[position])
    }

    override fun getItemCount(): Int {
       return  list.size
    }
}