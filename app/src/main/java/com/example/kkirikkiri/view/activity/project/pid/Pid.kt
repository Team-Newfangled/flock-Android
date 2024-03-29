package com.example.kkirikkiri.view.activity.project.pid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.pid.PidAdapter
import com.example.kkirikkiri.view.recyclerview.pid.PidItem
import com.example.kkirikkiri.viewmodel.BoardModel
import java.util.ArrayList

class Pid : BaseActivity<ActivityPidBinding>({ ActivityPidBinding.inflate(it) }) {

    private val model = BoardModel()

    private var list = ArrayList<PidItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.clear()
        model.findBoardPage(UserInfo.projectId!!,0)

        observe()

        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))

        binding.plus.setOnClickListener {
            Intent(this, WritePid::class.java).run { startActivity(this) }
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        list.clear()
        model.findBoardPage(UserInfo.projectId!!,0)
        observe()
        refresh()
    }

    private fun observe() {
        model.boardList.observe(this) {
            for (i in it) {
                list.add(PidItem(i.content, i.id, i.writerId))
                binding.pidRecyclerview.adapter = PidAdapter(list, this, intent)
            }
        }
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}