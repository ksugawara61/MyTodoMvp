package net.listadoko.mytodomvp.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)
