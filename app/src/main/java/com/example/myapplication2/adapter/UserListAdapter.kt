package com.example.myapplication2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.UserResponseItem
import com.example.myapplication2.databinding.UsersListItemBinding


class UserListAdapter (private val mList: ArrayList<UserResponseItem>, var context :Context) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
with(holder){
    val ItemsViewModel = mList[position]

    // sets the text to the textview from our itemHolder class
    binding.txtName.text =   "Name:   "+ItemsViewModel.Name
    binding.txtEmail.text =  "Email:  "+ItemsViewModel.Email
    binding.txtMobile.text = "Mobile: "+ItemsViewModel.Mobile
    binding.txtGender.text = "Gender: "+ItemsViewModel.Gender
}


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val binding=UsersListItemBinding.bind(itemView)
    }
}