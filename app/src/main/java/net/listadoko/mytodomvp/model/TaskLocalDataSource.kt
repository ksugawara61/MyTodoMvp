package net.listadoko.mytodomvp.model

import net.listadoko.mytodomvp.util.AppExecutors
import net.listadoko.mytodomvp.model.local.AppDatabase
import net.listadoko.mytodomvp.model.local.Task

class TaskLocalDataSource(val appExecutors: AppExecutors, val db: AppDatabase) {

    interface LoadTaskCallback {
        fun onTaskListLoaded(taskList: List<Task>)
    }

    interface GetTaskCallback {
        fun onTaskLoaded(task: Task)
    }

    fun insert(task: Task) {
        db.taskDao().insert(task)
    }

    fun findAll(): List<Task> {
        return db.taskDao().findAll()
    }

    fun getTaskList(callback: LoadTaskCallback) {
        appExecutors.diskIO.execute {
            val taskList = db.taskDao().findAll()
            appExecutors.mainThread.execute {
                callback.onTaskListLoaded(taskList)
            }
        }
    }

    fun getTask(taskId: String, callback: GetTaskCallback) {
        appExecutors.diskIO.execute {
            val task = db.taskDao().find(taskId)
            appExecutors.mainThread.execute {
                callback.onTaskLoaded(task)
            }
        }
    }
}