package com.ferbajoo.composevsxml.ui.features.chatXml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ferbajoo.composevsxml.R
import kotlin.random.Random

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = 30

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvName = itemView.findViewById<TextView>(R.id.tv_user_name)
        private val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        private val tvMessage = itemView.findViewById<TextView>(R.id.tv_message)
        private val ivImage = itemView.findViewById<ImageView>(R.id.iv_user_image)
        val loremIpsum = LoremIpsum(Random.nextInt(1, 5)).values.shuffled()
        fun bind(position: Int) {
            tvName.text = itemView.context.getString(R.string.name, position.toString())
            tvDate.text = itemView.context.getString(R.string.date, if (position < 10) "0$position" else position.toString())
            tvMessage.text = loremIpsum.joinToString()
            ivImage.load("https://randomuser.me/api/portraits/men/$position.jpg")
        }

    }

}