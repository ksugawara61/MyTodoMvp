package net.listadoko.mytodomvp.view.taskdetail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.view.BaseActivity
import net.listadoko.mytodomvp.view.taskadd.TaskAddActivity

class TaskDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        setupFabContent(findViewById(R.id.fab_add_task))

        // TODO: implement presenter logic
        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
