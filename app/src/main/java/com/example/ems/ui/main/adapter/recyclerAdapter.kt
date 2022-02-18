package com.example.ems.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ems.R
import com.example.ems.data.model.Employee
import com.example.ems.utils.AdapterClickListener

class recyclerAdapter(var data:List<Employee>, var context: Context, var listener:AdapterClickListener): RecyclerView.Adapter<recyclerAdapter.MyViewHolder>() {
    class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var name: TextView?=null
        var band:TextView?=null
        var designation:TextView?=null
        var project:TextView?=null
        var btnEdit:ImageView?=null


        init {
            name=item.findViewById(R.id.name)
            band=item.findViewById(R.id.band)
            designation=item.findViewById(R.id.designation)
            project=item.findViewById(R.id.project)
            btnEdit=item.findViewById(R.id.Edit)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name!!.text=data.get(position).employeeName
        holder.band!!.text=data.get(position).employeeBand
        holder.designation!!.text=data.get(position).designation
        holder.project!!.text=data.get(position).project
        holder.btnEdit!!.setOnClickListener(View.OnClickListener {
            listener.onItemClick(position)
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}