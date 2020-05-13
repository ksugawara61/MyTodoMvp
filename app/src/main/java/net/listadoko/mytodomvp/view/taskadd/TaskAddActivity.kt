package net.listadoko.mytodomvp.view.taskadd

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.taskadd.TaskAddContract
import net.listadoko.mytodomvp.presentation.taskadd.TaskAddPresenter
import net.listadoko.mytodomvp.util.Injection
import net.listadoko.mytodomvp.view.BaseActivity

class TaskAddActivity : BaseActivity(), TaskAddContract.View {

    override lateinit var presenter: TaskAddContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
        presenter = TaskAddPresenter(
            taskId,
            Injection.provideTaskListRepository(applicationContext),
            this
        )
        presenter.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showTask(task: Task) {
        with(findViewById<TextView>(R.id.task_add_title)) {
            text = task.title
        }
        with(findViewById<TextView>(R.id.task_add_description)) {
            text = task.description
        }
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }
}
