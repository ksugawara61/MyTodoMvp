package net.listadoko.mytodomvp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM tasks")
    fun findAll(): List<Task>
}
