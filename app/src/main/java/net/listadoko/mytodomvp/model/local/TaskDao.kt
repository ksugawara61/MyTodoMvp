package net.listadoko.mytodomvp.model.local

import androidx.room.*
import net.listadoko.mytodomvp.model.local.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Long

    @Update
    fun update(task: Task): Int

    @Query("SELECT * FROM tasks")
    fun findAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun find(taskId: String): Task

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    fun updateCompleted(taskId: String, isCompleted: Boolean)
}
