package com.example.kkirikkiri.view.recyclerview.myteam.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemTodayTodosBinding
import com.example.kkirikkiri.module.network.dto.todo.TodayTodoResponse

class TodayTodoAdapter(val list : List<TodayTodoResponse.TodoContentResponse>) : RecyclerView.Adapter<TodayTodoAdapter.Holder>() {
    inner class Holder(var binding : ItemTodayTodosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setTodos(item : TodayTodoResponse.TodoContentResponse) {
            binding.todayTodosText.text = item.todo_content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemTodayTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setTodos(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}