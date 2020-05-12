package net.listadoko.mytodomvp.presentation.tasklist

import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.BasePresenter
import net.listadoko.mytodomvp.presentation.BaseView

interface TaskListContract {

    interface View : BaseView<Presenter> {
        fun showTaskList(taskList: List<Task>)

        fun showTaskDetailPage(task: Task)
    }

    interface Presenter : BasePresenter {
        fun loadTaskList(forceUpdate: Boolean)

        fun openTaskDetail(task: Task)
    }
}
