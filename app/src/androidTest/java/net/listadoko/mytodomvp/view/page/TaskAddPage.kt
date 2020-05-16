package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo

class TaskAddPage : BasePage() {
    fun editTitle(text: String): TaskAddPage {
        val editText = onView(allOf(withId(R.id.task_add_title), isDisplayed()))
        editText.perform(replaceText(text), closeSoftKeyboard())
        return this
    }

    fun editDescription(text: String): TaskAddPage {
        val editText = onView(allOf(withId(R.id.task_add_description), isDisplayed()))
        editText.perform(replaceText(text), closeSoftKeyboard())
        return this
    }

    fun saveTask(): TaskListPage {
        val floatingActionButton = onView(allOf(withId(R.id.fab_save_task), isDisplayed()))
        floatingActionButton.perform(click())
        return TaskListPage()
    }

    fun assertTask(title: String, description: String): TaskAddPage {
        return assertTitle(title).assertDescription(description)
    }

    fun assertTitle(title: String): TaskAddPage {
        val editText = onView(allOf(withId(R.id.task_add_title), isDisplayed()))
        editText.check(matches(withText(equalTo(title))))
        return this
    }

    fun assertDescription(description: String): TaskAddPage {
        val editText = onView(allOf(withId(R.id.task_add_description), isDisplayed()))
        editText.check(matches(withText(equalTo(description))))
        return this
    }
}
