package net.listadoko.mytodomvp.view

import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
        setSupportActionBar(findViewById(toolbarId))
        supportActionBar?.run {
            action()
        }
    }
}