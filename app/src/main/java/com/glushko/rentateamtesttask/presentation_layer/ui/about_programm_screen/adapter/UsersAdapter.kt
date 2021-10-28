package com.glushko.rentateamtesttask.presentation_layer.ui.about_programm_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glushko.rentateamtesttask.R
import com.glushko.rentateamtesttask.business_logic_layer.domain.User

class UsersAdapter(private var list: List<User> = listOf(), val callback: Callback):
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    fun setList(users: List<User>, start: Int, count: Int){
        list = users
        notifyItemRangeChanged(start, count)
    }

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        private val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        fun onBind(user: User) {
            tvFirstName.text = user.first_name
            tvLastName.text = user.last_name
            itemView.setOnClickListener {
                callback.onClickUser(user)
            }
        }
    }

    interface Callback{
        fun onClickUser(user: User)
    }

}