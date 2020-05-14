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
            updateTask(title, description, taskId)
        }
    }

    private fun createTask(title: String, description: String) {
        val task = Task(title = title, description = description)
        source.saveTask(task, object : TaskLocalDataSource.SaveTaskCallback {
            override fun onSaveTaskLoaded(isSave: Boolean) {
                if (isSave) {
                    view.showTaskList()
                } else {
                    // TODO: show error message
                }
            }
        })
    }

    private fun updateTask(title: String, description: String, taskId: String) {
        val task = Task(title = title, description = description, id = taskId)
        source.saveTask(task, object : TaskLocalDataSource.SaveTaskCallback {
            override fun onSaveTaskLoaded(isSave: Boolean) {
                if (isSave) {
                    view.showTaskList()
                } else {
                    // TODO: show error message
                }
            }
        })
    }
}
