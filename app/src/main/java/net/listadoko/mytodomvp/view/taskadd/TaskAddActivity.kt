package net.listadoko.mytodomvp.view.taskadd

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        setupFabContent(findViewById(R.id.fab_save_task))

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

    override fun showSaveResult(isSave: Boolean) {
        val text = if (isSave) "保存しました" else "保存できませんでした"
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun setupFabContent(fab: FloatingActionButton) {
        fab.setOnClickListener {
            val title = findViewById<TextView>(R.id.task_add_title).text.toString()
            val description = findViewById<TextView>(R.id.task_add_description).text.toString()
            presenter.saveTask(title, description)
        }
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }
}
