package net.listadoko.mytodomvp.model

class TaskLocalDataSource(val db: AppDatabase) {
    fun insert(task: Task) {
        db.taskDao().insert(task)
    }

    fun findAll(): List<Task> {
        return db.taskDao().findAll()
    }
}