package net.listadoko.mytodomvp.view


import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.listadoko.mytodomvp.view.page.TaskListPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TaskListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TaskListActivity::class.java)

    @Test
    fun taskListActivityTest() {
        TaskListPage()
            .assertHelloWorld("All TO-DOs")
            .goToTaskAdd()
            .assertTest("Add TO-DO")
    }
}
