package net.listadoko.mytodomvp.presentation.taskadd

import net.listadoko.mytodomvp.model.TaskLocalDataSource
import net.listadoko.mytodomvp.model.local.Task

class TaskAddPresenter(
    private val taskId: String?,
    private val source: TaskLocalDataSource,
    val view: TaskAddContract.View
) : TaskAddContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
        if (taskId != null) {
            source.getTask(taskId, object : TaskLocalDataSource.GetTaskCallback {
                override fun onTaskLoaded(task: Task) {
                    view.showTask(task)
                }
            })
        }
    }

    override fun saveTask(title: String, description: String) {
        if (taskId == null) {
            createTask(title, description)
        } else {
            updateTask(title, description)
        }
    }

    private fun createTask(title: String, description: String) {
        // TODO: create task
    }

    private fun updateTask(title: String, description: String) {
        // TODO update task
    }
}
