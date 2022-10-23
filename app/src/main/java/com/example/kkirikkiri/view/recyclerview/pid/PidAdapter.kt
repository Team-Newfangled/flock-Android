package com.example.kkirikkiri.view.recyclerview.pid

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemPidBigBinding
import com.example.kkirikkiri.view.activity.project.pid.ChangePid
import com.example.kkirikkiri.view.activity.project.pid.PidView
import com.example.kkirikkiri.viewmodel.BoardModel

class PidAdapter(val list : List<PidItem>, val activity: Activity, val intent: Intent) : RecyclerView.Adapter<PidAdapter.Holder>() {
    inner class Holder(var binding : ItemPidBigBinding, val activity: Activity, val intent: Intent) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("수정", "삭제")
        private val model = BoardModel()

        fun setPid(item : PidItem) {
            binding.pidBigId.text = item.id.toString()
            binding.pidBigTitle.text = item.title

            itemView.setOnClickListener {
                Intent(itemView.context, PidView::class.java)
                    .putExtra("title", item.title)
                    .putExtra("id", item.id)
                    .putExtra("writeId", item.writeId)
                    .run { itemView.context.startActivity(this) }
            }

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) {dialog, pos ->
                    when (pos) {
                        0 -> {
                            Intent(itemView.context, ChangePid::class.java)
                                .putExtra("id", item.id)
                                .run { itemView.context.startActivity(this) }
                            dialog.dismiss()
                        }
                        1 -> {
                            model.deleteBoard(item.id)
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
        val binding = ItemPidBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding, activity, intent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pid = list[position]
        holder.setPid(pid)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}