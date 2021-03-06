package net.listadoko.mytodomvp.view


import androidx.room.Room
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import net.listadoko.mytodomvp.model.local.AppDatabase
import net.listadoko.mytodomvp.view.page.TaskListPage
import net.listadoko.mytodomvp.view.tasklist.TaskListActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@LargeTest
@RunWith(AndroidJUnit4::class)
class TaskListActivityTest {

    lateinit var db: AppDatabase

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TaskListActivity::class.java)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // TODO: fix database name
        db = Room.databaseBuilder(context, AppDatabase::class.java, "Tasks.db")
            .allowMainThreadQueries()
            .build()
        db.clearAllTables()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun addTaskTest() {
        TaskListPage()
            .assertTitle("All TO-DOs")
            .goToTaskAdd()
            .editTitle("sample title")
            .editDescription("sample description")
            .saveTask()
            .assertTask("sample title")
    }

    @Test
    fun showTaskDetailTest() {
        TaskListPage()
            .assertTitle("All TO-DOs")
            .goToTaskAdd()
            .editTitle("sample detail title")
            .editDescription("sample detail description")
            .saveTask()
            .goToTaskDetail("sample detail title")
            .assertCompleted(false)
            .clickTaskComplete()
            .assertTask("sample detail title", "sample detail description", true)
    }

    @Test
    fun editTaskTest() {
        TaskListPage()
            .assertTitle("All TO-DOs")
            .goToTaskAdd()
            .editTitle("sample title")
            .editDescription("sample description")
            .saveTask()
            .goToTaskDetail("sample title")
            .goToTaskAdd()
            .assertTask("sample title", "sample description")
            .assertTitle("sample title")
            .editTitle("sample edit title")
            .saveTask()
            .assertTask("sample edit title")
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
