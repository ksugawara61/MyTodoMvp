package net.listadoko.mytodomvp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.listadoko.mytodomvp.R

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        val fab = findViewById<FloatingActionButton>(R.id.fab_add_task)
        fab.setOnClickListener {
            val intent = Intent(this, TaskAddActivity::class.java)
            startActivity(intent)
        }
    }
}