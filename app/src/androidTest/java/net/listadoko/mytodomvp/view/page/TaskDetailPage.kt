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

    fun clickTaskComplete(): TaskDetailPage {
        val checkBox = onView(allOf(withId(R.id.task_detail_complete), isDisplayed()))
        checkBox.perform(click())
        return this
    }

    fun assertTask(title: String, description: String, isCompleted: Boolean): TaskDetailPage {
        return assertTitle(title).assertDescription(description).assertCompleted(isCompleted)
    }

    fun assertTitle(title: String): TaskDetailPage {
        val textView = onView(allOf(withId(R.id.task_detail_title), isDisplayed()))
        textView.check(matches(withText(Matchers.equalTo(title))))
        return this
    }

    fun assertDescription(description: String): TaskDetailPage {
        val textView = onView(allOf(withId(R.id.task_detail_description), isDisplayed()))
        textView.check(matches(withText(Matchers.equalTo(description))))
        return this
    }

    fun assertCompleted(isCompleted: Boolean): TaskDetailPage {
        val checkBox = onView(allOf(withId(R.id.task_detail_complete), isDisplayed()))
        checkBox.check(matches(isChecked(isCompleted)))
        return this
    }
}
