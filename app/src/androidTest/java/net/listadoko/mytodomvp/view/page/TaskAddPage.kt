package net.listadoko.mytodomvp.view.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import net.listadoko.mytodomvp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

class TaskAddPage {
    fun assertTest(text: String): TaskAddPage {
        val textView = onView(allOf(withId(R.id.add_todo_text), isDisplayed()))
        textView.check(ViewAssertions.matches(withText(Matchers.equalTo(text))))
        return this
    }
}