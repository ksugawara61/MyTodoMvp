package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskDetailPage : BasePage() {
    fun goToTaskList(): TaskListPage {
        pressBack()
        return TaskListPage()
    }

    fun goToTaskAdd(): TaskAddPage {
        val fab = onView(allOf(withId(R.id.fab_add_task), isDisplayed()))
        fab.perform(click())
        return TaskAddPage()
    }

    fun assertTask(title: String, description: String, isCompleted: Boolean): TaskDetailPage {
        val titleTextView = onView(allOf(withId(R.id.task_detail_title), isDisplayed()))
        val descriptionTextView = onView(allOf(withId(R.id.task_detail_description), isDisplayed()))
        val checkBox = onView(allOf(withId(R.id.task_detail_complete), isDisplayed()))

        titleTextView.check(matches(withText(Matchers.equalTo(title))))
        descriptionTextView.check(matches(withText(Matchers.equalTo(description))))
        val checked = if (isCompleted) isChecked() else isNotChecked()
        checkBox.check(matches(checked))
        return this
    }

    fun clickTaskComplete(): TaskDetailPage {
        val checkBox = onView(allOf(withId(R.id.task_detail_complete), isDisplayed()))
        checkBox.perform(click())
        return this
    }
}
