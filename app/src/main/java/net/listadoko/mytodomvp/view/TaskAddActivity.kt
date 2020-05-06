package net.listadoko.mytodomvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.listadoko.mytodomvp.R

class TaskAddActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)
    }
}