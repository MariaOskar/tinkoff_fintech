package com.moonpi.swiftnotes

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.util.getDevice
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers

class SwiftNotesApplication {
    private val MORE_BUTTON_DESCRIPTION = "Ещё"

    fun checkApplicationTitle(text: String): SwiftNotesApplication {
        onView(withText(text)).check(matches(isDisplayed()))
        return this
    }

    fun checkNoNotesPlaceholder(text: String): SwiftNotesApplication {
        onView(withId(R.id.noNotes)).check(matches(isDisplayed()))
        onView(withId(R.id.noNotes)).check(matches(withText(text)))
        return this
    }

    fun clickOnNewNoteButton(): SwiftNotesApplication {
        onView(withId(R.id.newNote)).check(matches(isDisplayed()))
        onView(withId(R.id.newNote)).perform(click())
        return this
    }

    fun checkNoteTitlePlaceholder(text: String): SwiftNotesApplication {
        onView(withId(R.id.titleEdit)).check(matches(withHint(text)))
        return this
    }

    fun checkNoteBodyPlaceholder(text: String): SwiftNotesApplication {
        onView(withId(R.id.bodyEdit)).check(matches(withHint(text)))
        return this
    }

    fun clickOnBackButton(): SwiftNotesApplication {
        onView(
            allOf(
                withClassName(containsString("ImageButton")),
                withParent(withId(R.id.toolbarEdit))
            )
        ).perform(click())
        return this
    }

    fun checkDialogMessage(text: String): SwiftNotesApplication {
        onView(withId(android.R.id.content)).check(matches(isDisplayed()))
        onView(withId(android.R.id.message)).check(matches(withText(text)))
        return this
    }

    fun checkAcceptButtonOnDialog(): SwiftNotesApplication {
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        return this
    }

    fun checkCancelButtonOnDialog(): SwiftNotesApplication {
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
        return this
    }

    fun pressAcceptButtonOnDialog(): SwiftNotesApplication {
        onView(withId(android.R.id.button1)).perform(click())
        return this
    }

    fun pressCancelButtonOnDialog(): SwiftNotesApplication {
        onView(withId(android.R.id.button2)).perform(click())
        return this
    }


    fun typeNoteTitle(text:String): SwiftNotesApplication {
        onView(withId(R.id.titleEdit))
            .perform(ViewActions.clearText())
            .perform(ViewActions.replaceText(text))
        return this
    }

    fun typeNoteBody(text:String): SwiftNotesApplication {
        onView(withId(R.id.bodyEdit))
            .perform(ViewActions.clearText())
            .perform(ViewActions.replaceText(text))
        return this
    }

    fun checkNoteWithTitleAndBody(title:String, body:String): SwiftNotesApplication {
        onView(Matchers.allOf(withId(R.id.titleView), withText(title)))
        onView(Matchers.allOf(withId(R.id.bodyView), withText(body)))
        return this
    }

    fun checkNotesCount(min:Int): SwiftNotesApplication {
        onView(withId(R.id.listView)).check(matches(hasMinimumChildCount(min)))
        return this
    }

    fun pressMoreButton(): SwiftNotesApplication {
        onView(withContentDescription(MORE_BUTTON_DESCRIPTION)).perform(click())
        return this
    }

    fun checkMenuItem(text:String): SwiftNotesApplication {
        onView(
            Matchers.allOf(
                withParent(withParent(withId(R.id.content))),
                withId(R.id.title),
                withText(text)
            )
        ).check(matches(isDisplayed()))
        return this
    }

    fun pressBackButtonOnDevice(): SwiftNotesApplication {
        getDevice().pressBack()
        return this
    }
}
