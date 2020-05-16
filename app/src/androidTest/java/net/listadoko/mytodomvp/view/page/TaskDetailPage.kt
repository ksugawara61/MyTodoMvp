package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskDetailPage : BasePage() {
    fun assertTask(text: String): TaskDetailPage {
        // TODO: checkbox test
        val textView = onView(
            allOf(
                withId(R.id.task_detail_title),
                childAtPosition(
                    allOf(
                        withId(R.id.task_detail_container),
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
        textView.check(matches(withText(Matchers.equalTo(text))))
        return this
    }

    fun clickTaskComplete(): TaskDetailPage {
        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.task_detail_complete),
                childAtPosition(
                    allOf(
                        withId(R.id.task_detail_container),
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
        appCompatCheckBox.perform(click())
        return this
    }
}
