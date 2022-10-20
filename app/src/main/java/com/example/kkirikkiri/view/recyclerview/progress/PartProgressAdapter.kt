package com.example.kkirikkiri.view.recyclerview.progress

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemProjectBinding
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem

class PartProgressAdapter(val list : List<TeamMemberProjectItem>) : RecyclerView.Adapter<PartProgressAdapter.Holder>() {

    inner class Holder(val binding : ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("수정", "삭제")

        @SuppressLint("SetTextI18n")
        fun setPartProgress(item : TeamMemberProjectItem) {
            binding.projectId.text = item.id.toString()
            binding.itemProjectPercent.text = "${item.percent}%"
            binding.itemProjectName.text = item.name

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) {dialog, pos ->
                    when (pos) {
                        0 -> {
                            dialog.dismiss()
                        }
                        1 -> {
                            dialog.dismiss()
                        }
                    }
                }.show()
                false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setPartProgress(list[position])
    }

    override fun getItemCount(): Int {
       return  list.size
    }
}