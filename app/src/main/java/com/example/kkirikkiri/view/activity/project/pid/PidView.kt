package com.example.kkirikkiri.view.activity.project.pid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidViewBinding
import com.example.kkirikkiri.view.recyclerview.pid.comment.CommentAdapter
import com.example.kkirikkiri.viewmodel.BoardModel
import com.example.kkirikkiri.viewmodel.TodoModel

class PidView : AppCompatActivity() {

    private val binding by lazy { ActivityPidViewBinding.inflate(layoutInflater) }

    private val model = BoardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val id = intent.getIntExtra("id", 0)
        model.findComment(id, 0)
        observe()
        val title = intent.getStringExtra("title")
        val writer = intent.getStringExtra("writeId")

        binding.pidViewContent.text = title
    }

    private fun observe() {
        model.findCommentData.observe(this) {
            binding.pidViewComment.adapter = CommentAdapter(it)
        }
    }
}