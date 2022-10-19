package com.example.kkirikkiri.view.activity.team

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.widget.*
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

    private var list = ArrayList<TeamMemberItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_manage)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        val joinLink = "http://141.164.59.254:8080/teams/${id}/join"

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

            val copy = dialog.findViewById<ImageView>(R.id.imageView2)
            val acp = dialog.findViewById<Button>(R.id.member_acp)
            val cancel = dialog.findViewById<Button>(R.id.member_cancel)
            val text = dialog.findViewById<TextView>(R.id.link)

            text.text = joinLink

            copy.setOnClickListener {
                createClip(joinLink)
            }

            acp.setOnClickListener {
                val link = Intent(Intent.ACTION_SEND)
                link.type = "text/plain"
                link.putExtra(Intent.EXTRA_TEXT, joinLink)
                startActivity(Intent.createChooser(link, "팀 초대 링크 공유하기"))
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

    private fun createClip(link : String) {
        val clipManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Invite", link)

        clipManager.setPrimaryClip(clip)
        Toast.makeText(this, "복사 완료!", Toast.LENGTH_SHORT).show()
    }
}