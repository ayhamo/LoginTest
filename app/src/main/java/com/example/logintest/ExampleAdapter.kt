package com.example.logintest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter() : RecyclerView.Adapter<ExampleAdapter.UserViewHolder>() {


    private lateinit var mllistener: AdapterView.OnItemClickListener
    private var items: MutableList<User> = ArrayList()

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mllistener = listener
    }

    fun deleteItem(i: Int) {
        items.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(i: Int, user: User) {
        items.add(i, user)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false),
            mllistener as OnItemClickListener
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount() = items.size

    fun submitList(blogList: MutableList<User>) {
        items = blogList
    }


    class UserViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.user_image
        val username: TextView = itemView.username
        val email: TextView = itemView.userEmail

        fun bind(user: User) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(user.image)
                .into(userImage)
            username.text = user.username
            email.text = user.email

        }


        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}