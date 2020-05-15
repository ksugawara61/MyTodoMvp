package net.listadoko.mytodomvp.presentation.taskdetail

import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.BasePresenter
import net.listadoko.mytodomvp.presentation.BaseView

interface TaskDetailContract {

    interface View : BaseView<Presenter> {
        fun showTask(task: Task)
    }

    interface Presenter : BasePresenter {
        fun completeTask(task: Task)

        fun activateTask(task: Task)
    }
}
