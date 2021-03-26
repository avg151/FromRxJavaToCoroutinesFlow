package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fromrxjavatocoroutinesflow.R
import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var data = listOf<SuccessDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(newData: List<SuccessDataModel>) {
        data = newData
        notifyDataSetChanged()
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView: TextView

    init {
        with(itemView) {
            titleTextView = findViewById(R.id.main_item_name)
        }
    }

    fun bind(state: SuccessDataModel) {
        titleTextView.text = state.name
    }
}