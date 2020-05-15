package net.listadoko.mytodomvp.presentation.taskdetail

import net.listadoko.mytodomvp.model.TaskLocalDataSource
import net.listadoko.mytodomvp.model.local.Task

class TaskDetailPresenter(
    private val taskId: String,
    private val source: TaskLocalDataSource,
    val view: TaskDetailContract.View
) : TaskDetailContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
        source.getTask(taskId, object : TaskLocalDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task) {
                view.showTask(task)
            }
        })
    }

    override fun completeTask(task: Task) {
        source.completeTask(task.id)
    }

    override fun activateTask(task: Task) {
        source.activateTask(task.id)
    }
}
