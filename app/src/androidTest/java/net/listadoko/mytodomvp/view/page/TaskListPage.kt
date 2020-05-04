package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskListPage {
    fun assertHelloWorld(text: String): TaskListPage {
        val textView = onView(allOf(ViewMatchers.withId(R.id.hello), ViewMatchers.isDisplayed()))
        textView.check(matches(ViewMatchers.withText(Matchers.equalTo(text))))
        return this
    }
}