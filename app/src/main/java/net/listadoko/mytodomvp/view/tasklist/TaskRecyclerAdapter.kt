package net.listadoko.mytodomvp.view.tasklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.view.BaseRecyclerAdapter

class TaskRecyclerAdapter(context: Context) : BaseRecyclerAdapter<Task>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(inflater.inflate(R.layout.adapter_task, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = list.getOrNull(position)

        if (item != null) {
            with(holder.view.findViewById<CheckBox>(R.id.task_checkbox)) {
                // TODO check item event
                isChecked = item.isCompleted
            }

            with(holder.view.findViewById<TextView>(R.id.task_title)) {
                text = item.title
            }

            holder.view.setOnClickListener {
                // TODO: implement to open task detail page.
            }
        }
    }

    override fun getItemCount() = list.size

    override fun updateItemList(list: List<Task>) {
        this.list = list
        notifyDataSetChanged()
    }
}
