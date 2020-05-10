package net.listadoko.mytodomvp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {
    protected var list: List<T> = emptyList()

    abstract fun updateItemList(list: List<T>)

    class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
