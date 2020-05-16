package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskAddPage : BasePage() {
    fun assertTest(text: String): TaskAddPage {
        val textView = onView(allOf(withId(R.id.task_add_title), isDisplayed()))
        textView.check(ViewAssertions.matches(withText(Matchers.equalTo(text))))
        return this
    }

    fun editTitle(text: String): TaskAddPage {
        val editText = onView(
            allOf(withId(R.id.task_add_title),
                childAtPosition(
                    allOf(
                        withId(R.id.task_add_container),
                        childAtPosition(
                            withClassName(Matchers.`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText(text), closeSoftKeyboard())
        return this
    }

    fun editDescription(text: String): TaskAddPage {
        val editText = onView(
            allOf(withId(R.id.task_add_description),
                childAtPosition(
                    allOf(
                        withId(R.id.task_add_container),
                        childAtPosition(
                            withClassName(Matchers.`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText(text), closeSoftKeyboard())
        return this
    }

    fun saveTask(): TaskListPage {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab_save_task),
                childAtPosition(
                    childAtPosition(
                        withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())
        return TaskListPage()
    }
}
