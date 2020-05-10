package net.listadoko.mytodomvp.view.tasklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.view.BaseRecyclerAdapter

class TaskRecyclerAdapter(context: Context) : BaseRecyclerAdapter<Task>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(inflater.inflate(R.layout.adapter_todo, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = list.getOrNull(position)

        // TODO tap item event
    }

    override fun getItemCount() = list.size

    override fun updateItemList(list: List<Task>) {
        this.list = list
        notifyDataSetChanged()
    }
}
