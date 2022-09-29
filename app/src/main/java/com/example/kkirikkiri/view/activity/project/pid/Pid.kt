package com.example.kkirikkiri.view.activity.project.pid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.pid.PidAdapter
import com.example.kkirikkiri.view.recyclerview.pid.PidItem
import com.example.kkirikkiri.viewmodel.BoardModel
import java.util.ArrayList

class Pid : AppCompatActivity() {

    private lateinit var binding: ActivityPidBinding

    private val model = BoardModel()

    private var list = ArrayList<PidItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pid)
        observe()

        val intent = intent.getIntExtra("id",0)

        model.findBoardPage(intent,20)

        val adapter = PidAdapter(list)
        binding.pidRecyclerview.adapter = adapter
        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))

        binding.textView11.setOnClickListener { Intent(this, WritePid::class.java).run { startActivity(this) } }
        binding.plus.setOnClickListener { Intent(this, WritePid::class.java).run { startActivity(this) } }
    }

    fun observe() {
        model.boardList.observe(this) {
            for (i in it) {
                list.add(PidItem(i.content, i.id, i.writerId))
            }
        }
    }

}