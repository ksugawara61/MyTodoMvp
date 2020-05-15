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

    interface SaveTaskCallback {
        fun onSaveTaskLoaded(isSave: Boolean)
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

    fun saveTask(task: Task, callback: SaveTaskCallback) {
        appExecutors.diskIO.execute {
            val isSave = db.taskDao().insert(task) >= 0 // NOTE: 0以上は登録成功
            appExecutors.mainThread.execute {
                callback.onSaveTaskLoaded(isSave)
            }
        }
    }

    fun updateTask(task: Task, callback: SaveTaskCallback) {
        appExecutors.diskIO.execute {
            val isSave = db.taskDao().update(task.id, task.title, task.description) > 0 // NOTE: 0より大きい場合更新成功
            appExecutors.mainThread.execute {
                callback.onSaveTaskLoaded(isSave)
            }
        }
    }

    fun completeTask(taskId: String) {
        appExecutors.diskIO.execute { db.taskDao().updateCompleted(taskId, true) }
    }

    fun activateTask(taskId: String) {
        appExecutors.diskIO.execute { db.taskDao().updateCompleted(taskId, false) }
    }
}
