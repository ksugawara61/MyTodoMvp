package net.listadoko.mytodomvp.view


import android.content.Context
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import net.listadoko.mytodomvp.R
import net.listadoko.mytodomvp.view.page.TaskListPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TaskListActivityTest {

    lateinit var context: Context

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TaskListActivity::class.java)

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun addTaskTest() {
        TaskListPage()
            .assertTitle("All TO-DOs")
            .goToTaskAdd()
            .assertTest("Add TO-DO")
    }

    @Test
    fun openDrawerTest() {
        TaskListPage()
            .openNavigationDrawer()
            .assertNavigationDrawerVisibility(true)
            .closeNavigationDrawer()
            .assertNavigationDrawerVisibility(false)
            .assertTitle("All TO-DOs")
    }
}
