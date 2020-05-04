package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskListPage {
    fun goToTaskAdd(): TaskAddPage {
        val fab = onView(allOf(withId(R.id.fab_add_task), isDisplayed()))
        fab.perform(click())
        return TaskAddPage()
    }

    fun assertHelloWorld(text: String): TaskListPage {
        val textView = onView(allOf(withId(R.id.all_todo_text), isDisplayed()))
        textView.check(matches(withText(Matchers.equalTo(text))))
        return this
    }
}