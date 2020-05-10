package net.listadoko.mytodomvp.util

import android.content.Context
import net.listadoko.mytodomvp.model.TaskLocalDataSource
import net.listadoko.mytodomvp.model.local.AppDatabase

object Injection {
    fun provideTaskListRepository(context: Context): TaskLocalDataSource {
        val database = AppDatabase.getInstance(context)
        return TaskLocalDataSource(AppExecutors(), database)
    }
}
