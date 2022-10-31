package com.example.kkirikkiri.view.activity.project.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityControlTodoBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.room.RoomImpl
import com.example.kkirikkiri.module.network.room.entity.TodoPercent

class ControlTodo : AppCompatActivity() {

    private val binding by lazy { ActivityControlTodoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getLongExtra("id",0)
        val percent = intent.getIntExtra("percent", 0)

        val helper = RoomImpl.getHelper(this)

        val todo = helper.todoPercentDao().getTodoById(id)

        binding.itemTodoName.text = todo.todoName
        binding.itemControlPercent.text = todo.percent.toString()
        binding.itemPercent.progress = todo.percent
        binding.itemPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
             binding.itemControlPercent.text = "$p1"
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        binding.todoBackButton.root.setOnClickListener { finish() }
        binding.controlTodoSave.setOnClickListener {
            val data = TodoPercent(UserInfo.teamId!!, UserInfo.projectId!!, todo.todoName, todo.writerName, binding.itemPercent.progress)
            data.id = id
            helper.todoPercentDao().insert(data)
            finish()
        }

    }
}