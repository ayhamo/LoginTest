package com.example.logintest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.grid_item.view.*

class GridItemAdapter : RecyclerView.Adapter<GridItemAdapter.UserViewHolder>() {

    private var items: MutableList<User> = ArrayList()

    fun submitList(userList: MutableList<User>) {
        items = userList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        when (holder) {
            is GridItemAdapter.UserViewHolder -> {
                holder.bind(items[position])
            }

        }
    }


    override fun getItemCount() = items.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.imageView3
        val username: TextView = itemView.textView3

        fun bind(user: User) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(user.image)
                .into(userImage)
            username.text = user.username

        }

    }
}