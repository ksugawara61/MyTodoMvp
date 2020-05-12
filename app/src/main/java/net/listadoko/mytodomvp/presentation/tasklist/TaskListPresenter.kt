package net.listadoko.mytodomvp.presentation.tasklist

import net.listadoko.mytodomvp.model.TaskLocalDataSource
import net.listadoko.mytodomvp.model.local.Task

class TaskListPresenter(
    private val source: TaskLocalDataSource,
    val view: TaskListContract.View
) : TaskListContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {
        loadTaskList(false)
    }

    override fun loadTaskList(forceUpdate: Boolean) {
        source.getTaskList(object : TaskLocalDataSource.LoadTaskCallback {
            override fun onTaskListLoaded(taskList: List<Task>) {
                view.showTaskList(taskList)
            }
        })
    }

    override fun openTaskDetail(task: Task) {
        view.showTaskDetailPage(task)
    }
}
