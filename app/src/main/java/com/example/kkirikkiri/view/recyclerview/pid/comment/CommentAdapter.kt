package com.example.kkirikkiri.view.recyclerview.pid.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemPidViewBinding
import com.example.kkirikkiri.module.dto.account.NameResponse
import com.example.kkirikkiri.module.dto.board.CommentResponse

class CommentAdapter(val list : List<CommentResponse>) : RecyclerView.Adapter<CommentAdapter.Holder>(){

    inner class Holder(var binding : ItemPidViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setComment(comment : CommentResponse) {
            binding.id.text = comment.id.toString()
            binding.boardId.text = comment.boardId.toString()
            binding.writeId.text = comment.writeId.toString()
            binding.itemPidViewComent.text = comment.comment
            binding.itemPidViewName.text = comment.writeId.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPidViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.setComment(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}