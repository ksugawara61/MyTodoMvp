package net.listadoko.mytodomvp.view.page

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskListPage : BasePage() {
    fun goToTaskAdd(): TaskAddPage {
        val fab = onView(allOf(withId(R.id.fab_add_task), isDisplayed()))
        fab.perform(click())
        return TaskAddPage()
    }

    fun goToTaskDetail(text: String): TaskDetailPage {
        val recyclerView = onView(
            allOf(withId(R.id.task_list_recycler_view),
                childAtPosition(
                    withId(R.id.task_list_container),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText(text)), click()))
        return TaskDetailPage()
    }

    fun openNavigationDrawer(): TaskListPage {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withId(R.id.app_bar_layout),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
        return this
    }

    fun closeNavigationDrawer(): TaskListPage {
        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(ViewActions.click())
        return TaskListPage()
    }

    fun assertTitle(text: String): TaskListPage {
        val textView = onView(allOf(withId(R.id.all_todo_text), isDisplayed()))
        textView.check(matches(withText(Matchers.equalTo(text))))
        return this
    }

    fun assertTask(text: String): TaskListPage {
        // TODO: checkbox test
        val textView = onView(
            allOf(
                withId(R.id.task_title),
                withText(text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.task_list_recycler_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText(Matchers.equalTo(text))))
        return this
    }

    fun assertNavigationDrawerVisibility(visible: Boolean): TaskListPage {
        val imageButton = onView(withId(R.id.nav_view))
        val matcher = if (visible) matches(isDisplayed()) else matches(Matchers.not(isDisplayed()))
        imageButton.check(matcher)
        return this
    }
}
