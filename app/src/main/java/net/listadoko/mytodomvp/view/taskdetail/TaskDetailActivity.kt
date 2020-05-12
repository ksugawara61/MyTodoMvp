package net.listadoko.mytodomvp.view.taskdetail

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.taskdetail.TaskDetailContract
import net.listadoko.mytodomvp.presentation.taskdetail.TaskDetailPresenter
import net.listadoko.mytodomvp.util.Injection
import net.listadoko.mytodomvp.view.BaseActivity
import net.listadoko.mytodomvp.view.taskadd.TaskAddActivity

class TaskDetailActivity : BaseActivity(), TaskDetailContract.View {

    override lateinit var presenter: TaskDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupFabContent(findViewById(R.id.fab_add_task))

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
        presenter = TaskDetailPresenter(
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
        with(findViewById<CheckBox>(R.id.task_detail_complete)) {
            isChecked = task.isCompleted
        }
        with(findViewById<TextView>(R.id.task_detail_title)) {
            text = task.title
        }
        with(findViewById<TextView>(R.id.task_detail_description)) {
            text = task.description
        }
    }

    private fun setupFabContent(fab: FloatingActionButton) {
        fab.setOnClickListener {
            val intent = Intent(this, TaskAddActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }
}
