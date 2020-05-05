package net.listadoko.mytodomvp.model

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(Enclosed::class)
class TaskLocalDataSourceTest {
    abstract class DBTest {
        lateinit var taskLocalDataSource: TaskLocalDataSource

        @Before
        fun setUpParent() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val db = Room.databaseBuilder(context, AppDatabase::class.java, "DB")
                .allowMainThreadQueries()
                .build()
            taskLocalDataSource = TaskLocalDataSource(db)
        }

        @After
        fun tearDownParent() { }
    }

    @RunWith(RobolectricTestRunner::class)
    class BlankRecord: DBTest() {
        @Test
        fun insert_finishesSuccessfully() {
            val task = Task(title = "purchase tomato", description = "purchase tomato")
            taskLocalDataSource.insert(task)
            val list = taskLocalDataSource.findAll()
            assertThat(list).hasSize(1)
        }

        @Test
        fun findAll_expectsEmpty() {
            val list = taskLocalDataSource.findAll()
            assertThat(list).isEmpty()
        }
    }

    @RunWith(RobolectricTestRunner::class)
    class RecordPrepared: DBTest() {
        @Before
        fun setUp() {
            val task = Task(title = "purchase tomato", description = "purchase tomato")
            taskLocalDataSource.insert(task)
        }

        @Test
        fun findAll_expectsNotEmpty() {
            val list = taskLocalDataSource.findAll()
            assertThat(list).isNotEmpty()
        }
    }
}