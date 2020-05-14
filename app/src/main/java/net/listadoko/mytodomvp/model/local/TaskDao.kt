package net.listadoko.mytodomvp.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.listadoko.mytodomvp.model.local.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Long

    @Query("SELECT * FROM tasks")
    fun findAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun find(taskId: String): Task
}
