package com.example.yasinpdf

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileRecyclerViewAdapter(val list : List<FileInfo> , val clickInterface: RvItemClickListener) : RecyclerView.Adapter<FileRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var tv: TextView = v.findViewById(R.id.tv_file_name_rv)
        var iv: ImageView = v.findViewById(R.id.iv_file_state)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickInterface.onClick(list[adapterPosition])
        }

        fun bindData(fileInfo: FileInfo){
            Log.d("TAGYAS", "bindData:${fileInfo.fullName} " )
            tv.text = fileInfo.fullName
            when(fileInfo.fileState){
                FileState.Folder -> iv.setImageResource(R.drawable.ic_folder)
                FileState.PDF -> iv.setImageResource(R.drawable.ic_pdf)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_rv, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        Log.d("TAGYAS", "bindData:${list.size} " )
        return list.size
    }
}