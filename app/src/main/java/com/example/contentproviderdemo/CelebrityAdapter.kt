package com.example.contentproviderdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_row.view.*
import javax.security.auth.callback.Callback

class CelebrityAdapter(var celebList: ArrayList<Celebrity>, val callBack: CelebrityCallback) :
    RecyclerView.Adapter<CelebrityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_row,
                parent,
                false
            )
        )


    override fun getItemCount(): Int {

        return celebList.size
    }

    override fun onBindViewHolder(holder: CelebrityAdapter.ViewHolder, position: Int) {

        holder.populateList(celebList[position])

    }

    fun updateList(passedList : ArrayList<Celebrity>) {
        celebList = passedList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun populateList(celebrity: Celebrity) {


            itemView.txtFirstName.text = celebrity.firstName
            itemView.txtLastName.text = celebrity.lastName
            itemView.txtJob.text = celebrity.job
            itemView.txtFav.text = celebrity.favorite
            itemView.txtId.text = celebrity.id

            itemView.setOnClickListener{ callBack.passCelebrity(celebrity)}

        }
    }
}