package net.listadoko.mytodomvp.view.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import net.listadoko.mytodomvp.util.Injection
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.model.local.Task
import net.listadoko.mytodomvp.presentation.tasklist.TaskListContract
import net.listadoko.mytodomvp.presentation.tasklist.TaskListPresenter
import net.listadoko.mytodomvp.view.BaseActivity
import net.listadoko.mytodomvp.view.taskadd.TaskAddActivity
import net.listadoko.mytodomvp.view.taskdetail.TaskDetailActivity

class TaskListActivity : BaseActivity(), TaskListContract.View {

    override lateinit var presenter: TaskListContract.Presenter

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerAdapter: TaskRecyclerAdapter
    private var itemListener: TaskRecyclerAdapter.TaskItemListener = object : TaskRecyclerAdapter.TaskItemListener {
        override fun onTaskClick(task: Task) {
            presenter.openTaskDetail(task)
        }

        override fun onCompleteTaskClick(task: Task) {
            presenter.completeTask(task)
        }

        override fun onActivateTaskClick(task: Task) {
            presenter.activateTask(task)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        setupActionBar(R.id.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }

        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById(R.id.nav_view))

        setupFabContent(findViewById(R.id.fab_add_task))

        findViewById<RecyclerView>(R.id.task_list_recycler_view).apply {
            recyclerAdapter = TaskRecyclerAdapter(context, itemListener)
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = recyclerAdapter
        }

        // setup presenter
        presenter =
            TaskListPresenter(
                Injection.provideTaskListRepository(applicationContext),
                this
            )
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.result(requestCode, resultCode)
    }

    override fun showTaskList(taskList: List<Task>) {
        recyclerAdapter.updateItemList(taskList)
    }

    override fun showTaskDetailPage(task: Task) {
        val intent = Intent(this, TaskDetailActivity::class.java).apply {
            putExtra(TaskDetailActivity.EXTRA_TASK_ID, task.id)
        }
        startActivity(intent)
    }

    override fun showSuccessfullySavedMessage() {
        val toast = Toast.makeText(applicationContext, "TODOを追加しました", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun setupFabContent(fab: FloatingActionButton) {
        fab.setOnClickListener {
            val intent = Intent(this, TaskAddActivity::class.java)
            startActivityForResult(intent, TaskAddActivity.REQUEST_ADD_TASK)
        }
    }
}
