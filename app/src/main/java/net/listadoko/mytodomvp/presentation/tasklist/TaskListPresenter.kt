package net.listadoko.mytodomvp.presentation.tasklist

import android.app.Activity
import net.listadoko.mytodomvp.model.TaskLocalDataSource
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.view.taskadd.TaskAddActivity

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

    override fun result(requestCode: Int, resultCode: Int) {
        if (requestCode == TaskAddActivity.REQUEST_ADD_TASK && resultCode == Activity.RESULT_OK) {
            view.showSuccessfullySavedMessage()
        }
    }
}
