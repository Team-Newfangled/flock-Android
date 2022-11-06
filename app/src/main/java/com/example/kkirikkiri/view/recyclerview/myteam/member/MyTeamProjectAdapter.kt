package com.example.kkirikkiri.view.recyclerview.myteam.member

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ItemProjectBinding
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.view.activity.project.Project
import com.example.kkirikkiri.viewmodel.ProjectModel

class MyTeamProjectAdapter(private val list: List<TeamMemberProjectItem>, private val intent: Intent, private val activity: Activity) : RecyclerView.Adapter<MyTeamProjectAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding, intent, activity)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val project = list[position]

        holder.setProject(project)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class Holder(var binding : ItemProjectBinding, intent: Intent, activity: Activity) : RecyclerView.ViewHolder(binding.root) {
        private val arrayItem = arrayOf("수정", "삭제")
        private val model = ProjectModel()

        @SuppressLint("SetTextI18n")
        fun setProject(project : TeamMemberProjectItem) {

            binding.itemProjectName.text = project.name
            binding.itemProjectPercent.text = ""
            binding.projectId.text = project.id.toString()

            itemView.setOnLongClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setItems(arrayItem) {dialog, pos ->
                    when (pos) {
                        0 -> {
                            val d = Dialog(itemView.context)
                            d.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            d.requestWindowFeature(Window.FEATURE_NO_TITLE)
                            d.setContentView(R.layout.dialog_addteam)


                            val title = d.findViewById<TextView>(R.id.textView2)
                            val text = d.findViewById<EditText>(R.id.editText)
                            val acp = d.findViewById<Button>(R.id.button)
                            val cancel = d.findViewById<Button>(R.id.button2)

                            dialog.dismiss()
                            d.show()
                            title.text = "프로젝트 이름을 입력 해주세요"
                            text.hint = "프로젝트 이름"
                            acp.setOnClickListener {
                                if (text.text.isNotEmpty()){
                                    model.modifyProjectName(project.id, NameRequest(text.text.toString()))
                                    refresh()
                                } else Toast.makeText(itemView.context, "프로젝트 이름을 작성해주세요", Toast.LENGTH_SHORT).show()
                                d.dismiss()
                            }
                            cancel.setOnClickListener { d.dismiss() }
                        }
                        1 -> {
                            model.deleteProject(project.id)
                            refresh()
                            dialog.dismiss()
                        }
                    }
                }.show()
                false
            }

            itemView.setOnClickListener {
                Intent(itemView.context, Project::class.java).putExtra("id", project.id).putExtra("name", project.name).run { itemView.context.startActivity(this) }
            }

        }

        private fun refresh() {
            activity.finish()
            activity.overridePendingTransition(0,0)
            activity.startActivity(intent)
            activity.overridePendingTransition(0,0)
        }
    }

}
