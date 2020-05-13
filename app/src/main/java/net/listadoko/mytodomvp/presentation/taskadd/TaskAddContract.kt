package net.listadoko.mytodomvp.presentation.taskadd

import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.BasePresenter
import net.listadoko.mytodomvp.presentation.BaseView

interface TaskAddContract {

    interface View : BaseView<Presenter> {
        fun showTask(task: Task)

        fun showSaveResult(isSave: Boolean)
    }

    interface Presenter : BasePresenter {
        fun saveTask(title: String, description: String)
    }
}
