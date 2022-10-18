package com.example.kkirikkiri.view.activity.team

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityTeamManageBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.team.TeamMemberItem
import com.example.kkirikkiri.viewmodel.TeamJoinModel
import com.example.kkirikkiri.viewmodel.TeamModel
import java.util.ArrayList

class TeamManage : AppCompatActivity() {

    private lateinit var binding: ActivityTeamManageBinding

    private val model = TeamModel()
    private val joinModel = TeamJoinModel()

    private var list = ArrayList<TeamMemberItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_manage)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        model.getTeamMember(id, 0)

        observe()
        binding.teamManageRecyclerbiew.adapter = MyTeamAdapter(list)
        binding.teamManageRecyclerbiew.layoutManager = LinearLayoutManager(this)
        binding.teamManageRecyclerbiew.addItemDecoration(RecyclerDecorationHeight(30))

        binding.addTeamButton.setOnClickListener {
            val dialog = Dialog(this)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_addmember)

            dialog.show()

            val text = dialog.findViewById<EditText>(R.id.member_name)
            val acp = dialog.findViewById<Button>(R.id.member_acp)
            val cancel = dialog.findViewById<Button>(R.id.member_cancel)

            acp.setOnClickListener {
                if (Patterns.EMAIL_ADDRESS.matcher(text.text.toString()).matches()) {
                    joinModel.sendJoinMain(id, text.text.toString())
                    dialog.dismiss()
                } else Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun observe() {
        model.teamMembers.observe(this, Observer {
            for (i in it) {
                val entity = TeamMemberItem(i.name, i.id)
                list.add(entity)
                binding.teamManageRecyclerbiew.adapter = MyTeamAdapter(list)
            }
        })
    }
}