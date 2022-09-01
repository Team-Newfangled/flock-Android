package com.example.kkirikkiri.view.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityTeamManageBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.TeamMember

class TeamManage : AppCompatActivity() {

    private lateinit var binding: ActivityTeamManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_manage)

        val member1 = TeamMember("서주영", 1)
        val member2 = TeamMember("서주이", 1)
        val member3 = TeamMember("서주오", 1)
        val member4 = TeamMember("서주사", 1)
        val member5 = TeamMember("서주지", 1)
        val member6 = TeamMember("서주잔", 1)
        val list = listOf(member1, member2, member3, member4, member5, member6 )

        binding.teamManageRecyclerbiew.adapter = MyTeamAdapter(list)
        binding.teamManageRecyclerbiew.layoutManager = LinearLayoutManager(this)
        binding.teamManageRecyclerbiew.addItemDecoration(RecyclerDecorationHeight(30))

    }
}